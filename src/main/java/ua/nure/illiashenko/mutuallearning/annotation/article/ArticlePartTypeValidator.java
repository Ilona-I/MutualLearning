package ua.nure.illiashenko.mutuallearning.annotation.article;

import static ua.nure.illiashenko.mutuallearning.constants.ArticlePartType.TEXT;
import static ua.nure.illiashenko.mutuallearning.constants.ArticlePartType.CODE;
import static ua.nure.illiashenko.mutuallearning.constants.ArticlePartType.IMAGE;
import static ua.nure.illiashenko.mutuallearning.constants.ArticlePartType.FILE;
import static ua.nure.illiashenko.mutuallearning.constants.ArticlePartType.LINK;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArticlePartTypeValidator implements ConstraintValidator<ArticlePartTypeValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return TEXT.equals(value) || CODE.equals(value) || IMAGE.equals(value) || FILE.equals(value) || LINK.equals(
            value);
    }
}
