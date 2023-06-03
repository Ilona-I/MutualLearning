package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.nure.illiashenko.mutuallearning.constants.ReactionType;

@Entity
@NoArgsConstructor
@Data
public class UserComment {

    @Id
    private int id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "comment_id")
    private String commentId;
    private String role;
    private String reaction;
}
