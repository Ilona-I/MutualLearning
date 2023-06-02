package ua.nure.illiashenko.mutuallearning.entity;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.constants.ArticleStatus;

@Entity
@NoArgsConstructor
@Data
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Timestamp creationDateTime;
    private Timestamp lastUpdateDateTime;

    @Column(nullable = false)
    private ArticleStatus status;

    @OneToMany(mappedBy="article")
    private Set<ArticlePart> articleParts;

    @OneToMany(mappedBy="article")
    private Set<UserArticle> userArticles;

    @OneToMany(mappedBy="article")
    private Set<ArticleMark> articleMarks;

    @OneToMany(mappedBy="article")
    private Set<Test> tests;

    @OneToMany(mappedBy="article")
    private Set<Comment> comments;
}
