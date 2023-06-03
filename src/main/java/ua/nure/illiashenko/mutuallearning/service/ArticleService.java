package ua.nure.illiashenko.mutuallearning.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.illiashenko.mutuallearning.constants.ArticleStatus;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleForUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
import ua.nure.illiashenko.mutuallearning.entity.Article;
import ua.nure.illiashenko.mutuallearning.entity.ArticleMark;
import ua.nure.illiashenko.mutuallearning.entity.ArticlePart;
import ua.nure.illiashenko.mutuallearning.entity.Mark;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;
import ua.nure.illiashenko.mutuallearning.exception.article.ArticleNotFoundException;
import ua.nure.illiashenko.mutuallearning.mapper.ArticleMapper;
import ua.nure.illiashenko.mutuallearning.mapper.ArticlePartMapper;
import ua.nure.illiashenko.mutuallearning.mapper.MarkMapper;
import ua.nure.illiashenko.mutuallearning.repository.ArticleMarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticlePartRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.validation.ArticleValidator;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticlePartMapper articlePartMapper;
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
    private MarkMapper markMapper;

    public List<ArticleFileLinksResponse> createArticle(ArticleRequest articleRequest) {
        articleValidator.validateArticleRequest(articleRequest);

        final Article article = articleMapper.mapArticleRequestToArticle(articleRequest);
        final Timestamp creationDateTime = new Timestamp(System.currentTimeMillis());
        article.setCreationDateTime(creationDateTime);
        article.setStatus(ArticleStatus.ACTIVE);


        return createArticle(article, articleRequest);
    }


    public List<ArticleFileLinksResponse> editArticle(int id, ArticleRequest articleRequest) {
        articleValidator.validateArticleRequest(articleRequest);
        return null;
    }

    public ArticleForUpdateResponse getArticleForUpdate(int id) {
        return null;
    }

    public ArticleResponse getArticle(int id) {
        final Article article = articleRepository.findById(id).orElseThrow(() -> articleNotFoundById(id));
        final ArticleResponse articleResponse = articleMapper.mapArticleToArticleResponse(article);
        final ArticlePartResponse[] articlePartResponses =
            articlePartRepository.findByArticleId(id)
                .stream()
                .map(articlePart -> articlePartMapper.mapArticlePartToArticlePartResponse(articlePart))
                .collect(Collectors.toList())
                .toArray(ArticlePartResponse[]::new);

        final MarkResponse[] markResponses =
            articleMarkRepository.findByArticleId(id)
                .stream()
                .map(articleMark -> markMapper.mapToMarkResponse(markRepository.getById(articleMark.getMarkId())))
                .collect(Collectors.toList())
                .toArray(MarkResponse[]::new);
        articleResponse.setArticleParts(articlePartResponses);
        articleResponse.setMarks(markResponses);
        articleResponse.setReaction(userArticleRepository.findByUserLoginAndArticleId(getUserLogin(), id).getReaction());
        return articleResponse;
    }

    public List<ArticleListElementResponse> getArticles(String marksId, String articleType, String ownerType,
        String sortType, String searchLine, String searchType) {
        return null;
    }

    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public List<ArticleFileLinksResponse> createArticle(Article article, ArticleRequest articleRequest) {
        final ArticlePartRequest[] articlePartRequests = articleRequest.getArticleParts();
        Article savedArticle = articleRepository.save(article);

        final List<ArticleFileLinksResponse> articleFileLinksParts = new ArrayList<>();
        for (ArticlePartRequest articlePartRequest : articlePartRequests) {
            final ArticlePart articlePart = articlePartMapper.mapArticlePartRequestToArticlePart(articlePartRequest);
            if (articlePartRepository.existsById(articlePartRequest.getId())) {
                articlePart.setId(null);
                articlePart.setLink(null);
                int id = articlePartRepository.save(articlePart).getId();
                articlePart.setId(id);
                articlePart.setLink(id + "-" + articlePartRequest.getLink());
            }
            articlePart.setArticleId(savedArticle.getId());
            articlePartRepository.save(articlePart);
            articleFileLinksParts.add(ArticleFileLinksResponse.builder()
                .id(articlePartRequest.getId())
                .link(articlePart.getLink())
                .type(articlePartRequest.getType())
                .build());
        }

        final UserArticle userArticle = new UserArticle();
        userArticle.setArticleId(savedArticle.getId());
        userArticle.setUserLogin(getUserLogin());
        userArticle.setRole(savedArticle.getType().equals(ArticleType.ARTICLE) ? ArticleUserRole.ARTICLE_CREATOR
            : ArticleUserRole.QUESTION_CREATOR);
        userArticleRepository.save(userArticle);

        for (String markId : articleRequest.getMarksId()) {
            int markIdInt = Integer.parseInt(markId);
            final ArticleMark articleMark = new ArticleMark();
            articleMark.setArticleId(savedArticle.getId());
            articleMark.setMarkId(markIdInt);
            articleMarkRepository.save(articleMark);
        }


        return articleFileLinksParts;
    }

    private ArticleNotFoundException articleNotFoundById(int id) {
        log.error("No article found by id: " + id);
        return new ArticleNotFoundException("articleNotFound",
            Collections.singletonMap("article", "No article found by id: " + id));
    }


    private String getUserLogin() {
        return "";
    }
}
