package ua.nure.illiashenko.mutuallearning.mapper;

import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartRequest;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.entity.ArticlePart;

@Component
public class ArticlePartMapper {

    public ArticlePartResponse mapArticlePartToArticlePartResponse(ArticlePart articlePart) {
        return ArticlePartResponse.builder()
            .id(articlePart.getId())
            .type(articlePart.getType())
            .text(articlePart.getText())
            .link(articlePart.getLink())
            .sequenceNumber(articlePart.getSequenceNumber())
            .build();
    }

    public ArticlePart mapArticlePartRequestToArticlePart(ArticlePartRequest articlePartRequest) {
        final ArticlePart articlePart = new ArticlePart();
        articlePart.setId(articlePartRequest.getId());
        articlePart.setText(articlePartRequest.getText());
        articlePart.setType(articlePartRequest.getType());
        articlePart.setLink(articlePartRequest.getId() + "-" + articlePartRequest.getLink());
        articlePart.setSequenceNumber(articlePartRequest.getSequenceNumber());
        return articlePart;
    }
}
