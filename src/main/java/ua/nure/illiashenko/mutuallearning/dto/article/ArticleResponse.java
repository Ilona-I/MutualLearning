package ua.nure.illiashenko.mutuallearning.dto.article;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.constants.ArticleStatus;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;
import ua.nure.illiashenko.mutuallearning.constants.ReactionType;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;

@Data
@Builder
public class ArticleResponse {

    private int id;
    private String title;
    private ArticleType type;
    private Timestamp creationDateTime;
    private Timestamp lastUpdateDateTime;
    private ArticlePartResponse[] articleParts;
    private MarkResponse[] marks;
    private ReactionType reaction;
    private ArticleStatus status;
}
