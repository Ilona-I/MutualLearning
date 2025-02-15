package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "article_id")
    private Integer articleId;
    private String login;
    @Column(name = "creation_date_time")
    private Timestamp creationDateTime;
    @Column(name = "last_update_date_time")
    private Timestamp lastUpdateDateTime;
    private String text;
}
