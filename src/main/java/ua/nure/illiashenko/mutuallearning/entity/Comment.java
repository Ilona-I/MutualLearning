package ua.nure.illiashenko.mutuallearning.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {

    private int id;
    private int articleId;
    private Date creationDateTime;
    private Date lastUpdateDateTime;
    private String link;
}
