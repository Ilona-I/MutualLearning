package ua.nure.illiashenko.mutuallearning.annotation.user;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.entity.User;
import ua.nure.illiashenko.mutuallearning.repository.UserRepository;

@RequiredArgsConstructor
@NoArgsConstructor
@Component
public class EmailValidator implements ConstraintValidator<EmailValidation, Object> {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private String[] fields;
    @NonNull
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        fields = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Map<String, String> fieldsValues = Arrays.stream(fields)
            .collect(Collectors.toMap(
                e -> e,
                e -> PARSER.parseExpression(e).getValue(value) != null
                    ? Objects.requireNonNull(PARSER.parseExpression(e).getValue(value)).toString()
                    : ""));
        String id = fieldsValues.get("login");
        String email = fieldsValues.get("email");
        if (id.isEmpty()) {
            return userRepository.findByEmail(email).isEmpty();
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getEmail().equals(email)
                || !user.getEmail().equals(email) && userRepository.findByEmail(email).isEmpty();
        }
        return true;
    }
}