package ua.nure.illiashenko.mutuallearning.annotation.user;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, Object> {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private String[] fields;

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
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
        String password = fieldsValues.get("password");
        String repeatPassword = fieldsValues.get("repeatedPassword");
        return password.equals(repeatPassword);
    }
}