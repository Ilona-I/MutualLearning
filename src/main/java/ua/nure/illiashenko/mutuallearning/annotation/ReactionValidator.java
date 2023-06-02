package ua.nure.illiashenko.mutuallearning.annotation;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ua.nure.illiashenko.mutuallearning.constants.ReactionType;

public class ReactionValidator implements ConstraintValidator<ReactionValidation, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Arrays.stream(ReactionType.values()).anyMatch(x-> x.toString().equals(value.toString()));
    }
}
