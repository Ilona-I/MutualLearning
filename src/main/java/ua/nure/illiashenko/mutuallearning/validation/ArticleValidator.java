package ua.nure.illiashenko.mutuallearning.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.exception.article.ArticleValidationException;
import ua.nure.illiashenko.mutuallearning.mapper.ConstraintViolationMapper;

@Slf4j
@Component
@AllArgsConstructor
public class ArticleValidator {

    @Autowired
    private final Validator validator;
    @Autowired
    private final ConstraintViolationMapper<ArticleRequest> constraintViolationMapper;

    public void validateArticleRequest(ArticleRequest articleRequest){
        Set<ConstraintViolation<ArticleRequest>> violations = validator.validate(articleRequest);
        if (!violations.isEmpty()) {
            log.error("Article wasn't saved because of invalid data. Total mistake count: {}", violations.size());
            throw new ArticleValidationException(
                "wrongArticleRequest",
                constraintViolationMapper.map(violations));
        }
    }
}
