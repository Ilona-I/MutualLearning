package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class ArticlePart {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=false)
    private Article article;

    @Column(nullable = false)
    private int sequenceNumber;

    private String text;

    private String link;

    @Column(nullable = false)
    private String type;
}
