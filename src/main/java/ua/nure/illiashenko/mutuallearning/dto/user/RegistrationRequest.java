package ua.nure.illiashenko.mutuallearning.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ua.nure.illiashenko.mutuallearning.annotation.user.CreateUserLoginValidation;
import ua.nure.illiashenko.mutuallearning.annotation.user.EmailValidation;
import ua.nure.illiashenko.mutuallearning.annotation.user.PasswordValidation;

@Data
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
@PasswordValidation({"password", "repeatedPassword"})
public class RegistrationRequest {

    @CreateUserLoginValidation
    private String login;
    private String password;
    private String repeatedPassword;
    private String name;
    @EmailValidation
    private String email;
    private String info;
}
