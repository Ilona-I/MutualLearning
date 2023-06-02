package ua.nure.illiashenko.mutuallearning.annotation.article;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ua.nure.illiashenko.mutuallearning.constants.ArticleType;

public class ArticleTypeValidator implements ConstraintValidator<ArticleTypeValidation, ArticleType> {

    @Override
    public boolean isValid(ArticleType value, ConstraintValidatorContext context) {
        return Arrays.asList(ArticleType.values()).contains(value);
    }
}
