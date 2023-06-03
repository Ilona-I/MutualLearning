package ua.nure.illiashenko.mutuallearning.mapper;

import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleListElementResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.entity.Article;

@Component
public class ArticleMapper {

    public ArticleResponse mapArticleToArticleResponse(Article article) {
        return ArticleResponse.builder()
            .id(article.getId())
            .type(article.getType())
            .title(article.getTitle())
            .creationDateTime(article.getCreationDateTime())
            .lastUpdateDateTime(article.getLastUpdateDateTime())
            .build();

    }

    public ArticleListElementResponse mapArticleToArticleListElementResponse(Article article){
        return ArticleListElementResponse.builder()
            .id(article.getId())
            .title(article.getTitle())
            .type(article.getType())
            .creationDateTime(article.getCreationDateTime())
            .lastUpdateDateTime(article.getLastUpdateDateTime())
            .build();

    }

    public Article mapArticleRequestToArticle(ArticleRequest articleRequest) {
        System.out.println("==================================== 36 ====================");
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setType(articleRequest.getType());
        return article;
    }
}
