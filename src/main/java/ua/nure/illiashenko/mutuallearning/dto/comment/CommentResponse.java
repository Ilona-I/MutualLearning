package ua.nure.illiashenko.mutuallearning.dto.comment;

import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.dto.article.Member;

@Data
@NoArgsConstructor
public class CommentResponse {

    private Integer id;
    private Member member;
    private Timestamp creationDateTime;
    private Timestamp lastUpdateDateTime;
    private String text;
}
