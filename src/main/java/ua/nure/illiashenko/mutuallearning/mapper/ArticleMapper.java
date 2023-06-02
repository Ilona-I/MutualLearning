package ua.nure.illiashenko.mutuallearning.mapper;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleResponse;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;
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
            .status(article.getStatus())
            .build();

    }

    public Article mapArticleRequestToArticle(ArticleRequest articleRequest) {
        Article article = new Article();
        article.setTitle(articleRequest.getTitle());
        article.setType(articleRequest.getType());
        return article;
    }
}
