package ua.nure.illiashenko.mutuallearning.entity;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Article {

    private int id;
    private String title;
    private String type;
    private Date creationDateTime;
    private Date lastUpdateDateTime;
    private String status;
}
