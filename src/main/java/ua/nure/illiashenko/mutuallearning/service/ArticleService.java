package ua.nure.illiashenko.mutuallearning.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.illiashenko.mutuallearning.constants.ArticleStatus;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleFileLinksResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleForUpdateResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.entity.Article;
import ua.nure.illiashenko.mutuallearning.entity.ArticleMark;
import ua.nure.illiashenko.mutuallearning.entity.ArticlePart;
import ua.nure.illiashenko.mutuallearning.entity.UserArticle;
import ua.nure.illiashenko.mutuallearning.exception.article.ArticleNotFoundException;
import ua.nure.illiashenko.mutuallearning.mapper.ArticleMapper;
import ua.nure.illiashenko.mutuallearning.repository.ArticleMarkRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticlePartRepository;
import ua.nure.illiashenko.mutuallearning.repository.ArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserArticleRepository;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;
import ua.nure.illiashenko.mutuallearning.validation.ArticleValidator;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
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


    public List<ArticleFileLinksResponse> createArticle(String login, ArticleRequest articleRequest) {
        articleValidator.validateArticleRequest(articleRequest);

        Article article = articleMapper.mapArticleRequestToArticle(articleRequest);
        Timestamp creationDateTime = new Timestamp(System.currentTimeMillis());
        article.setCreationDateTime(creationDateTime);
        article.setStatus(ArticleStatus.ACTIVE);

        String[] marksId = articleRequest.getMarksId();
        ArticlePartRequest[] articlePartRequests = articleRequest.getArticleParts();

        List<ArticleMark> articleMarks = new ArrayList<>();
        for(String markId: marksId){
            int markIdInt = Integer.parseInt(markId);

        }



        return null;
    }

    public List<ArticleFileLinksResponse> editArticle(int id, ArticleRequest articleRequest) {
        articleValidator.validateArticleRequest(articleRequest);
        return null;
    }

    public ArticleForUpdateResponse getArticleForUpdate(int id) {
        return null;
    }

    public ArticleResponse getArticle(int id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> articleNotFoundById(id));

        return null;
    }

    public List<ArticleListElementResponse> getArticles(String marksId, String articleType, String ownerType,
        String sortType, String searchLine, String searchType) {
        return null;
    }

    public void deleteArticle(int id) {

    }

   /*   @Transactional
    public Integer createArticle(Article article, List<ArticlePart> articleParts, UserArticle userArticle, List<ArticleMark> articleMarks) {
      int articleId = articleRepository.save(article).getId();
        userArticle.setArticleId(articleId);
        userArticleRepository.save(userArticle);
        for (ArticleMark articleMark : articleMarks) {
            articleMark.setArticleId(articleId);
            articleMarkRepository.save(articleMark);
        }
        for (ArticlePart articlePart : articleParts) {
            articlePart.setArticleId(articleId);
            articlePartRepository.save(articlePart);
        }
        return articleId;
    }*/

    private ArticleNotFoundException articleNotFoundById(int id) {
        log.error("No article found by id: " + id);
        return new ArticleNotFoundException("articleNotFound",
            Collections.singletonMap("article", "No article found by id: " + id));
    }
}
