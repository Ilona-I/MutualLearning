package ua.nure.illiashenko.mutuallearning.dto.article;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.dto.mark.MarkResponse;

@Data
@Builder
public class ArticleResponse {

    private int id;
    private String title;
    private String type;
    private Timestamp creationDateTime;
    private Timestamp lastUpdateDateTime;
    private ArticlePartResponse[] articleParts;
    private MarkResponse[] marks;
    private Member[] members;
    private TestTitleResponse[] tests;
}
