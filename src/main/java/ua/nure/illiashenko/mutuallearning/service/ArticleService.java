package ua.nure.illiashenko.mutuallearning.service;

import static ua.nure.illiashenko.mutuallearning.constants.ArticleType.ANSWERED_QUESTION;
import static ua.nure.illiashenko.mutuallearning.constants.ArticleType.ARTICLE;
import static ua.nure.illiashenko.mutuallearning.constants.ArticleType.QUESTION;
import static ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole.ARTICLE_CREATOR;
import static ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole.QUESTION_ANSWERER;
import static ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole.QUESTION_CREATOR;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.illiashenko.mutuallearning.constants.ArticlePartType;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleForUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.Member;
import ua.nure.illiashenko.mutuallearning.dto.article.TestTitleResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.entity.Article;
import ua.nure.illiashenko.mutuallearning.entity.ArticleMark;
import ua.nure.illiashenko.mutuallearning.entity.ArticlePart;
import ua.nure.illiashenko.mutuallearning.entity.Mark;
import ua.nure.illiashenko.mutuallearning.entity.User;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;
import ua.nure.illiashenko.mutuallearning.exception.AccessDeniedException;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;
import ua.nure.illiashenko.mutuallearning.exception.article.ArticleNotFoundException;
import ua.nure.illiashenko.mutuallearning.mapper.ArticleMapper;
import ua.nure.illiashenko.mutuallearning.mapper.ArticlePartMapper;
import ua.nure.illiashenko.mutuallearning.mapper.MarkMapper;
import ua.nure.illiashenko.mutuallearning.repository.ArticleMarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticlePartRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.TestRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserTestRepository;
import ua.nure.illiashenko.mutuallearning.validation.ArticleValidator;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticlePartMapper articlePartMapper;
    @Autowired
    private MarkMapper markMapper;
    @Autowired
    private ArticleValidator articleValidator;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserArticleRepository userArticleRepository;
    @Autowired
    private ArticleMarkRepository articleMarkRepository;
    @Autowired
    private ArticlePartRepository articlePartRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserTestRepository userTestRepository;

    @Transactional
    public List<ArticleFileLinksResponse> createArticle(String login, ArticleRequest articleRequest) {
        articleValidator.validateArticleRequest(articleRequest);
        final Article article = articleMapper.mapArticleRequestToArticle(articleRequest);
        article.setCreationDateTime(new Timestamp(System.currentTimeMillis()));
        return createArticle(login, article, articleRequest);
    }

    @Transactional
    public List<ArticleFileLinksResponse> editArticle(String login, int id, ArticleRequest articleRequest) {
        articleValidator.validateArticleRequest(articleRequest);
        final Optional<Article> optionalDbArticle = articleRepository.findById(id);
        if (optionalDbArticle.isEmpty()) {
            throw new ArticleNotFoundException();
        }
        final Article updatedArticle = new Article();
        if (isArticleOrAnsweredQuestion(optionalDbArticle)) {
            updatedArticle.setTitle(articleRequest.getTitle());
            updatedArticle.setType(optionalDbArticle.get().getType());
        } else if (optionalDbArticle.get().getType().equals(QUESTION)) {
            setForQuestionType(login, id, optionalDbArticle, updatedArticle);
        }
        updatedArticle.setId(id);
        updatedArticle.setCreationDateTime(optionalDbArticle.get().getCreationDateTime());
        updatedArticle.setLastUpdateDateTime(new Timestamp(System.currentTimeMillis()));
        return updateArticle(updatedArticle, articleRequest);
    }

    private void setForQuestionType(String login, int id, Optional<Article> optionalDbArticle, Article updatedArticle) {
        updatedArticle.setTitle(optionalDbArticle.get().getTitle());
        updatedArticle.setType(ANSWERED_QUESTION);
        final UserArticle userArticle = new UserArticle();
        userArticle.setArticleId(id);
        userArticle.setUserLogin(login);
        userArticle.setRole(QUESTION_ANSWERER);
    }

    private boolean isArticleOrAnsweredQuestion(Optional<Article> optionalDbArticle) {
        return optionalDbArticle.isPresent() && optionalDbArticle.get().getType().equals(ARTICLE)
            || optionalDbArticle.get().getType().equals(ANSWERED_QUESTION);
    }

    public ArticleForUpdateResponse getArticleForUpdate(String login, int id) {
        final Article article = articleRepository.findById(id).orElseThrow(() -> articleNotFoundById(id));
        final UserArticle userArticle = userArticleRepository.findByUserLoginAndArticleId(login, id)
            .orElseThrow(ArticleNotFoundException::new);
        if (checkForAccess(article, userArticle)) {
            throw new AccessDeniedException();
        }
        final ArticleForUpdateResponse articleForUpdateResponse = articleMapper.mapArticleToArticleForUpdateResponse(
            article);
        final ArticlePartResponse[] articlePartResponses = getArticlePartResponses(id);
        final MarkResponse[] markResponses = getMarkResponses(id);
        articleForUpdateResponse.setArticleParts(articlePartResponses);
        articleForUpdateResponse.setMarks(markResponses);
        return articleForUpdateResponse;
    }

    private boolean checkForAccess(Article article, UserArticle userArticle) {
        return article.getType().equals(ARTICLE) && !userArticle.getRole().equals(ARTICLE_CREATOR) ||
            article.getType().equals(ANSWERED_QUESTION) && !userArticle.getRole().equals(QUESTION_ANSWERER);
    }

    public ArticleResponse getArticle(String login, int id) {
        final Article article = articleRepository.findById(id).orElseThrow(() -> articleNotFoundById(id));
        final ArticleResponse articleResponse = articleMapper.mapArticleToArticleResponse(article);
        final ArticlePartResponse[] articlePartResponses = getArticlePartResponses(id);
        final MarkResponse[] markResponses = getMarkResponses(id);
        articleResponse.setArticleParts(articlePartResponses);
        articleResponse.setMarks(markResponses);
        articleResponse.setMembers(getMembers(article).toArray(Member[]::new));
        articleResponse.setTests(getArticleTestTitles(id));
        return articleResponse;
    }

    private TestTitleResponse[] getArticleTestTitles(int articleId) {
        return testRepository.findByArticleId(articleId)
            .stream()
            .map(test -> TestTitleResponse.builder()
                .id(test.getId())
                .title(test.getTitle())
                .build())
            .toArray(TestTitleResponse[]::new);
    }

    public List<ArticleListElementResponse> getArticles(String login, Integer[] marksId, String articleType,
        String ownerType, String searchLine, String searchType, int page, int size) {
        String searchLineText = searchLine != null ? searchLine.trim() : "";
        final Set<Integer> articlesId = getArticlesId(login, marksId, ownerType, searchType, searchLineText);
        Pageable limit = PageRequest.of(page, size);
        final Set<String> articleTypeSet = getArticleTypes(articleType);
        List<Article> foundArticles = getFoundArticles(searchType,
            searchLineText, articlesId, limit, articleTypeSet);
        return getArticleListElementResponses(foundArticles);
    }

    private List<Article> getFoundArticles(String searchType, String searchLineText, Set<Integer> articlesId,
        Pageable limit,
        Set<String> articleTypeSet) {
        List<Article> foundArticles;
        if ("by_title".equals(searchType) && !searchLineText.isEmpty()) {
            foundArticles = articleRepository.findAllByIdIsInAndTypeIsInAndTitleContainsOrderByCreationDateTimeDesc(
                articlesId, articleTypeSet, searchLineText, limit);
        } else {
            foundArticles = articleRepository.findAllByIdIsInAndTypeIsInOrderByCreationDateTimeDesc(articlesId,
                articleTypeSet, limit);
        }
        return foundArticles;
    }

    private List<ArticleListElementResponse> getArticleListElementResponses(List<Article> foundArticles) {
        final List<ArticleListElementResponse> responses = new ArrayList<>();
        for (Article article : foundArticles) {
            final ArticleListElementResponse articleListElement = articleMapper.mapArticleToArticleListElementResponse(
                article);
            articleListElement.setMarks(getMarkResponses(article).toArray(MarkResponse[]::new));
            articleListElement.setMembers(getMembers(article).toArray(Member[]::new));
            responses.add(articleListElement);
        }
        return responses;
    }

    private Set<String> getArticleTypes(String articleType) {
        Set<String> types = new HashSet<>();
        if (QUESTION.equalsIgnoreCase(articleType)) {
            types.add(QUESTION);
            types.add(ANSWERED_QUESTION);
        } else if (ARTICLE.equalsIgnoreCase(articleType)) {
            types.add(ARTICLE);
        } else {
            types.add(QUESTION);
            types.add(ARTICLE);
            types.add(ANSWERED_QUESTION);
        }
        return types;
    }

    @Transactional
    public void deleteArticle(String login, int id) {
        userArticleRepository.findByUserLoginAndArticleId(login,id)
            .orElseThrow(()-> new ServiceApiException(HttpStatus.NOT_FOUND));
        articleRepository.deleteById(id);
        userArticleRepository.deleteAllByArticleId(id);
        testRepository.deleteAllByArticleId(id);
    }

    public List<ArticleFileLinksResponse> createArticle(String login, Article article, ArticleRequest articleRequest) {
        final ArticlePartRequest[] articlePartRequests = articleRequest.getArticleParts();
        Article savedArticle = articleRepository.save(article);
        final List<ArticleFileLinksResponse> articleFileLinksParts = createArticleParts(articlePartRequests,
            savedArticle);
        createUserArticle(login, savedArticle);
        addMarksToArticle(articleRequest, savedArticle);
        return articleFileLinksParts;
    }

    private void addMarksToArticle(ArticleRequest articleRequest, Article savedArticle) {
        for (String markId : articleRequest.getMarksId()) {
            int markIdInt = Integer.parseInt(markId);
            final ArticleMark articleMark = new ArticleMark();
            articleMark.setArticleId(savedArticle.getId());
            articleMark.setMarkId(markIdInt);
            articleMarkRepository.save(articleMark);
        }
    }

    public List<ArticleFileLinksResponse> updateArticle(Article article, ArticleRequest articleRequest) {
        articleRepository.save(article);
        final List<ArticleFileLinksResponse> articleFileLinksParts = updateArticleParts(article, articleRequest);
        updateArticleMarks(article, articleRequest);
        return articleFileLinksParts;
    }

    private void updateArticleMarks(Article article, ArticleRequest articleRequest) {
        List<Integer> oldArticleMarksId = articleMarkRepository.findByArticleId(article.getId()).stream()
            .map(ArticleMark::getMarkId)
            .collect(Collectors.toList());
        List<Integer> newArticleMarksId = Arrays.asList(articleRequest.getMarksId()).stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        deleteRemovedArticleMarks(article, oldArticleMarksId, newArticleMarksId);
        addMarksToArticle(article, oldArticleMarksId, newArticleMarksId);
    }

    private void addMarksToArticle(Article article, List<Integer> oldArticleMarksId, List<Integer> newArticleMarksId) {
        List<Integer> articleMarksIdToAdd = new ArrayList<>(newArticleMarksId);
        articleMarksIdToAdd.removeAll(oldArticleMarksId);
        for (Integer markId : articleMarksIdToAdd) {
            ArticleMark articleMark = new ArticleMark();
            articleMark.setMarkId(markId);
            articleMark.setArticleId(article.getId());
            articleMarkRepository.save(articleMark);
        }
    }

    private void deleteRemovedArticleMarks(Article article, List<Integer> oldArticleMarksId,
        List<Integer> newArticleMarksId) {
        List<Integer> articleMarksIdToDelete = new ArrayList<>(oldArticleMarksId);
        articleMarksIdToDelete.removeAll(newArticleMarksId);
        for (Integer markId : articleMarksIdToDelete) {
            final ArticleMark articleMark = articleMarkRepository.findByArticleIdAndMarkId(article.getId(), markId);
            articleMarkRepository.delete(articleMark);
        }
    }

    private List<ArticleFileLinksResponse> createArticleParts(ArticlePartRequest[] articlePartRequests,
        Article savedArticle) {
        final List<ArticleFileLinksResponse> articleFileLinksParts = new ArrayList<>();
        for (ArticlePartRequest articlePartRequest : articlePartRequests) {
            final ArticlePart articlePart = articlePartMapper.mapArticlePartRequestToArticlePart(articlePartRequest);
            checkForExistence(articlePartRequest, articlePart);
            checkType(articlePartRequest, articlePart);
            articlePart.setArticleId(savedArticle.getId());
            articlePartRepository.save(articlePart);
            articleFileLinksParts.add(ArticleFileLinksResponse.builder()
                .id(articlePartRequest.getId())
                .link(articlePart.getLink())
                .type(articlePartRequest.getType())
                .build());
        }
        return articleFileLinksParts;
    }

    private void checkType(ArticlePartRequest articlePartRequest, ArticlePart articlePart) {
        if (isFileOrImage(articlePart)) {
            articlePartRequest.setLink(articlePartRequest.getLink()
                .replace(articlePartRequest.getId() + "-", ""));
            articlePart.setLink(articlePart.getId() + "-" + articlePartRequest.getLink());
        }
    }

    private void checkForExistence(ArticlePartRequest articlePartRequest, ArticlePart articlePart) {
        if (articlePartRepository.existsById(articlePartRequest.getId())) {
            articlePart.setId(null);
            if (isFileOrImage(articlePart)) {
                articlePart.setLink(null);
                int id = articlePartRepository.save(articlePart).getId();
                articlePart.setId(id);
                articlePart.setLink(id + "-" + articlePartRequest.getLink());
            }
        }
    }

    private boolean isFileOrImage(ArticlePart articlePart) {
        return articlePart.getType().equals(ArticlePartType.FILE)
            || articlePart.getType().equals(ArticlePartType.IMAGE);
    }

    private List<ArticleFileLinksResponse> updateArticleParts(Article article,
        ArticleRequest articleRequest) {
        List<ArticlePart> newArticleParts = Arrays.stream(articleRequest.getArticleParts())
            .map(articlePartRequest -> {
                ArticlePart articlePart = articlePartMapper.mapArticlePartRequestToArticlePart(articlePartRequest);
                articlePart.setArticleId(article.getId());
                checkType(articlePartRequest, articlePart);
                return articlePart;
            }).collect(Collectors.toList());
        deleteRemovedArticleParts(article, newArticleParts);
        return saveUpdatedArticleParts(article, newArticleParts);
    }

    private List<ArticleFileLinksResponse> saveUpdatedArticleParts(Article article,
        List<ArticlePart> newArticleParts) {
        final List<ArticleFileLinksResponse> articleFileLinksParts = new ArrayList<>();
        for (ArticlePart newArticlePart : newArticleParts) {
            final Optional<ArticlePart> dbArticlePart = articlePartRepository.findById(newArticlePart.getId());
            if (dbArticlePart.isPresent() && !dbArticlePart.get().getArticleId().equals(article.getId())) {
                int id = newArticlePart.getId();
                newArticlePart.setId(null);
                if (isFileOrImage(newArticlePart)) {
                    String link = newArticlePart.getLink().replace(id + "-", "");
                    newArticlePart.setLink(null);
                    int newId = articlePartRepository.save(newArticlePart).getId();
                    newArticlePart.setId(newId);
                    newArticlePart.setLink(newId + "-" + link);
                }
            }
            articlePartRepository.save(newArticlePart);
            articleFileLinksParts.add(ArticleFileLinksResponse.builder()
                .id(newArticlePart.getId())
                .link(newArticlePart.getLink())
                .type(newArticlePart.getType())
                .build());
        }
        return articleFileLinksParts;
    }

    private void deleteRemovedArticleParts(Article article, List<ArticlePart> newArticleParts) {
        List<Integer> oldArticlePartsId = articlePartRepository.findByArticleId(article.getId()).stream()
            .map(ArticlePart::getId)
            .collect(Collectors.toList());
        List<Integer> newArticlePartsId = newArticleParts.stream()
            .map(ArticlePart::getId)
            .collect(Collectors.toList());

        List<Integer> articlePartsIdToDelete = new ArrayList<>(oldArticlePartsId);
        articlePartsIdToDelete.removeAll(newArticlePartsId);
        articlePartRepository.deleteAllById(articlePartsIdToDelete);
    }

    private Set<Integer> getArticlesId(String login, Integer[] marksId, String ownerType, String searchType,
        String searchLineText) {
        final Set<Integer> articlesIdByMarks = getArticlesIdByMarks(marksId);
        final Set<Integer> articlesIdByOwnType = getArticlesIdByOwnerType(login, ownerType);
        final Set<Integer> articlesIdByLoginSearch = getArticlesIdByLoginSearch(searchLineText, searchType);
        Set<Integer> articlesId = new HashSet<>(articlesIdByMarks);
        if (articlesIdByOwnType != null) {
            articlesId.retainAll(articlesIdByOwnType);
        }
        if (articlesIdByLoginSearch != null) {
            articlesId.retainAll(articlesIdByLoginSearch);
        }
        return articlesId;
    }

    private Set<Integer> getArticlesIdByLoginSearch(String searchLineText, String searchType) {
        if (!searchLineText.isEmpty() && "by_login".equals(searchType)) {
            return userArticleRepository.findAllByUserLoginContains(searchLineText)
                .stream()
                .map(UserArticle::getArticleId)
                .collect(Collectors.toSet());
        }
        return null;
    }

    private Set<Integer> getArticlesIdByOwnerType(String login, String ownerType) {
        Set<Integer> articlesIdByOwnType = null;
        if ("OWN".equalsIgnoreCase(ownerType)) {
            articlesIdByOwnType = userArticleRepository
                .findAllByUserLoginAndRoleIsIn(login, getArticleCreatorRoles()).stream()
                .map(UserArticle::getArticleId)
                .collect(Collectors.toSet());
        }
        return articlesIdByOwnType;
    }

    private Set<Integer> getArticlesIdByMarks(Integer[] marksId) {
        if (marksId == null || marksId.length == 0) {
            Set<Integer> result = articleRepository.findAll()
                .stream()
                .map(Article::getId)
                .collect(Collectors.toSet());
            result.removeAll(articleMarkRepository.findAll()
                .stream()
                .map(ArticleMark::getArticleId)
                .collect(Collectors.toSet()));
            return result;
        }
        return articleMarkRepository.findAllByMarkIdIsIn(List.of(marksId)).stream()
            .map(ArticleMark::getArticleId).collect(Collectors.toSet());
    }

    private List<Member> getMembers(Article article) {
        final List<Member> members = new ArrayList<>();
        final List<UserArticle> userArticles = userArticleRepository.findAllByArticleIdAndRoleIsIn(article.getId(),
            getArticleCreatorRoles());

        for (UserArticle userArticle : userArticles) {
            final User user = userRepository.getById(userArticle.getUserLogin());
            final Member member = Member.builder()
                .login(user.getLogin())
                .name(user.getName())
                .info(user.getInfo())
                .articleRole(userArticle.getRole())
                .build();
            members.add(member);
        }
        return members;
    }

    private void createUserArticle(String login, Article savedArticle) {
        final UserArticle userArticle = new UserArticle();
        userArticle.setArticleId(savedArticle.getId());
        userArticle.setUserLogin(login);
        userArticle.setRole(savedArticle.getType().equals(ArticleType.ARTICLE) ? ARTICLE_CREATOR
            : QUESTION_CREATOR);
        userArticleRepository.save(userArticle);
    }

    private MarkResponse[] getMarkResponses(int id) {
        return articleMarkRepository.findByArticleId(id)
            .stream()
            .map(articleMark -> markMapper.mapToMarkResponse(markRepository.getById(articleMark.getMarkId())))
            .collect(Collectors.toList())
            .toArray(MarkResponse[]::new);
    }

    private ArticlePartResponse[] getArticlePartResponses(int id) {
        return articlePartRepository.findByArticleIdOrderBySequenceNumberAsc(id)
            .stream()
            .map(articlePart -> articlePartMapper.mapArticlePartToArticlePartResponse(articlePart))
            .collect(Collectors.toList())
            .toArray(ArticlePartResponse[]::new);
    }

    private List<String> getArticleCreatorRoles() {
        return Arrays.asList(ARTICLE_CREATOR,
            QUESTION_CREATOR,
            QUESTION_ANSWERER);
    }

    private List<MarkResponse> getMarkResponses(Article article) {
        final List<MarkResponse> markResponseList = new ArrayList<>();
        final List<ArticleMark> articleMarks = articleMarkRepository.findByArticleId(article.getId());
        for (ArticleMark articleMark : articleMarks) {
            final Mark mark = markRepository.getById(articleMark.getMarkId());
            final MarkResponse markResponse = markMapper.mapToMarkResponse(mark);
            markResponseList.add(markResponse);
        }
        return markResponseList;
    }

    private ArticleNotFoundException articleNotFoundById(int id) {
        log.error("No article found by id: " + id);
        throw new ArticleNotFoundException();
    }
}
