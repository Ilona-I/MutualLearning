package ua.nure.illiashenko.mutuallearning.annotation.article;

import static ua.nure.illiashenko.mutuallearning.constants.ArticleType.ARTICLE;
import static ua.nure.illiashenko.mutuallearning.constants.ArticleType.QUESTION;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArticleTypeValidator implements ConstraintValidator<ArticleTypeValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ARTICLE.equals(value)||QUESTION.equals(value);
    }
}
