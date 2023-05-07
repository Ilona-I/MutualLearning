package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Article {

    private int id;
    private String title;
    private String type;
    private Timestamp creationDateTime;
    private Timestamp lastUpdateDateTime;
    private String status;
}
