package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String type;
    @Column(name = "creation_date_time")
    private Timestamp creationDateTime;
    @Column(name = "last_update_date_time")
    private Timestamp lastUpdateDateTime;
}
