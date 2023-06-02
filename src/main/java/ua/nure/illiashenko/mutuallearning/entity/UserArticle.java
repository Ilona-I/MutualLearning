package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.constants.ArticleUserRole;
import ua.nure.illiashenko.mutuallearning.constants.ReactionType;

@Entity
@NoArgsConstructor
@Data
public class UserArticle {

    @Id
    private int id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "article_Id")
    private Integer articleId;
    private ArticleUserRole role;
    private ReactionType reaction;
}
