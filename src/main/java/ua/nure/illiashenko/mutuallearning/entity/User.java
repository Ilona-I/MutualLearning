package ua.nure.illiashenko.mutuallearning.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String login;
    private String password;
    private String name;
    private String email;
    private String info;
    private String role;
    private String level;
    private String status;
}
