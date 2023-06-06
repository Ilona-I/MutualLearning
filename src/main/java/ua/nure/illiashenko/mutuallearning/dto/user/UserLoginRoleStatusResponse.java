package ua.nure.illiashenko.mutuallearning.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRoleStatusResponse {

    private String login;
    private String role;
    private String status;
}
