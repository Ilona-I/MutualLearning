package ua.nure.illiashenko.mutuallearning.mapper;

import ua.nure.illiashenko.mutuallearning.dto.article.ArticlePartResponse;
import ua.nure.illiashenko.mutuallearning.entity.ArticlePart;

public class ArticlePartMapper {

    public ArticlePartResponse mapToArticlePartResponse(ArticlePart articlePart) {
        return ArticlePartResponse.builder()
            .id(articlePart.getId())
            .type(articlePart.getType())
            .text(articlePart.getText())
            .link(articlePart.getLink())
            .sequenceNumber(articlePart.getSequenceNumber())
            .build();

    }
}
