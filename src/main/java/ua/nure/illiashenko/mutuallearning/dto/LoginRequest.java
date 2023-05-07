package ua.nure.illiashenko.mutuallearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String login;
    private String password;
}
