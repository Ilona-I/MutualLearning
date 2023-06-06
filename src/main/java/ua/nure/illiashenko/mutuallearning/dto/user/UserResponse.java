package ua.nure.illiashenko.mutuallearning.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private String login;
    private String name;
    private String email;
    private String info;
    private String role;
    private String status;
}
