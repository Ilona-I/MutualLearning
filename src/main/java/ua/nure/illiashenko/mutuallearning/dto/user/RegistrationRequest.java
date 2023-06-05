package ua.nure.illiashenko.mutuallearning.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.nure.illiashenko.mutuallearning.annotation.user.CreateUserLoginValidation;
import ua.nure.illiashenko.mutuallearning.annotation.user.EmailValidation;
import ua.nure.illiashenko.mutuallearning.annotation.user.PasswordValidation;

@Data
@AllArgsConstructor
@EmailValidation({"login", "email"})
@PasswordValidation({"password", "repeatPassword"})
public class RegistrationRequest {

    @CreateUserLoginValidation
    private String login;
    private String password;
    private String repeatedPassword;
    private String name;
    private String email;
    private String info;
}
