package ua.nure.illiashenko.mutuallearning.dto.article;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ua.nure.illiashenko.mutuallearning.annotation.article.ArticleTypeValidation;
import ua.nure.illiashenko.mutuallearning.annotation.mark.MarksIdValidation;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    @NotEmpty(message = "emptyArticleRequestTitle")
    private String title;

    @NotEmpty(message = "emptyArticleRequestType")
    @ArticleTypeValidation
    private String type;

    private ArticlePartRequest[] articleParts;

    @MarksIdValidation
    private String[] marksId;
}
