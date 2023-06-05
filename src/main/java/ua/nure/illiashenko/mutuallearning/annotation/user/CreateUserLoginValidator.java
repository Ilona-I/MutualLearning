package ua.nure.illiashenko.mutuallearning.annotation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;

@AllArgsConstructor
@Component
public class CreateUserLoginValidator implements ConstraintValidator<CreateUserLoginValidation, String> {

    @NonNull
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        return !userRepository.existsById(login);
    }
}
