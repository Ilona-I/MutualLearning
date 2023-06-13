package ua.nure.illiashenko.mutuallearning.dto.article;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.annotation.article.ArticlePartTypeValidation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePartRequest {

    private int id;

    @NotEmpty(message = "emptyArticlePartRequestSequenceNumber")
    private int sequenceNumber;

    private String text;

    private String link;

    @NotEmpty(message = "emptyArticlePartRequestType")
    @ArticlePartTypeValidation
    private String type;
}
