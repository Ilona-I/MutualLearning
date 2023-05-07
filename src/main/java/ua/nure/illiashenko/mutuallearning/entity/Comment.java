package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {

    private int id;
    private int articleId;
    private Timestamp creationDateTime;
    private Timestamp lastUpdateDateTime;
    private String link;
    private String status;
}
