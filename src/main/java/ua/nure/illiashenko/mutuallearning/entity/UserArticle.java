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
public class UserArticle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_login", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=false)
    private Article article;

    @Column(nullable = false)
    private String role;

    private int reaction;
}
