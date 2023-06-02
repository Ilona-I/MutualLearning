package ua.nure.illiashenko.mutuallearning.annotation.article;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ua.nure.illiashenko.mutuallearning.constants.ArticlePartType;

public class ArticlePartTypeValidator implements ConstraintValidator<ArticlePartTypeValidation, ArticlePartType> {

    @Override
    public boolean isValid(ArticlePartType value, ConstraintValidatorContext context) {
        return Arrays.asList(ArticlePartType.values()).contains(value);
    }
}
