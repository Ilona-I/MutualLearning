package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.constants.ArticlePartType;

@Entity
@NoArgsConstructor
@Data
public class ArticlePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "article_id")
    private Integer articleId;
    @Column(name = "sequence_number")
    private int sequenceNumber;
    private String text;
    private String link;
    private String type;
}
