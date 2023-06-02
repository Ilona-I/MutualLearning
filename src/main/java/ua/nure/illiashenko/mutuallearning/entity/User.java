package ua.nure.illiashenko.mutuallearning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(nullable = false)
    private String login;
    private String password;
    private String name;
    private String email;
    private String info;
    private String role;
    private String status;
}
