package ua.nure.illiashenko.mutuallearning.annotation.mark;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ua.nure.illiashenko.mutuallearning.util.DataValidator;

public class MarksIdValidator implements ConstraintValidator<MarksIdValidation, String[]> {

    @Override
    public boolean isValid(String[] marksId, ConstraintValidatorContext context) {
        for (String markId : marksId) {
            if (!DataValidator.isInteger(markId)) {
                return false;
            }
        }
        return true;
    }
}

