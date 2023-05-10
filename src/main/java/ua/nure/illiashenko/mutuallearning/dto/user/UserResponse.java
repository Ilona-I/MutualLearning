package ua.nure.illiashenko.mutuallearning.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private String login;
    private String name;
    private String email;
    private String info;
    private String role;
    private String level;
    private String status;
}
