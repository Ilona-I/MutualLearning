package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=false)
    private Article article;

    @Column(nullable = false)
    private Timestamp creationDateTime;

    private Timestamp lastUpdateDateTime;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy="comment")
    private Set<UserComment> userComments;
}
