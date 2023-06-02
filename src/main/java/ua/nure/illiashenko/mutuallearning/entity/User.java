package ua.nure.illiashenko.mutuallearning.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private String name;

    @Column(nullable = false)
    private String email;

    private String info;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy="user")
    private Set<UserArticle> userArticles;

    @OneToMany(mappedBy="user")
    private Set<UserTest> userTests;

    @OneToMany(mappedBy="user")
    private Set<UserComment> userComments;
}
