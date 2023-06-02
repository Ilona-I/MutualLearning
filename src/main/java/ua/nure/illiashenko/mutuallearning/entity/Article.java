package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.constants.ArticleStatus;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;

@Entity
@NoArgsConstructor
@Data
public class Article {

    @Id
    private Integer id;
    private String title;
    private ArticleType type;
    @Column(name = "creation_date_time")
    private Timestamp creationDateTime;
    @Column(name = "last_update_date_time")
    private Timestamp lastUpdateDateTime;
    private ArticleStatus status;

}
