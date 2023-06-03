package ua.nure.illiashenko.mutuallearning.annotation;

import static ua.nure.illiashenko.mutuallearning.constants.ReactionType.LIKE;
import static ua.nure.illiashenko.mutuallearning.constants.ReactionType.DISLIKE;
import static ua.nure.illiashenko.mutuallearning.constants.ReactionType.NOT_LIKE;
import static ua.nure.illiashenko.mutuallearning.constants.ReactionType.NOT_DISLIKE;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ReactionValidator implements ConstraintValidator<ReactionValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return LIKE.equals(value)||DISLIKE.equals(value)||NOT_LIKE.equals(value)||NOT_DISLIKE.equals(value);
    }
}
