package ua.nure.illiashenko.mutuallearning.dto.article;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.annotation.ReactionValidation;

@Data
@Builder
public class ArticleReactionRequest {

    @NotEmpty(message = "emptyArticleReactionRequestReaction")
    @ReactionValidation
    private String reaction;
}
