package ua.nure.illiashenko.mutuallearning.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    private String login;
    private String password;
    private String repeatedPassword;
    private String name;
    private String email;
    private String info;
}
