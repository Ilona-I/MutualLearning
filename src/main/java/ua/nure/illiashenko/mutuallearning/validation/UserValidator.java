package ua.nure.illiashenko.mutuallearning.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.article.ArticleRequest;
import ua.nure.illiashenko.mutuallearning.dto.user.RegistrationRequest;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;
import ua.nure.illiashenko.mutuallearning.exception.article.ArticleValidationException;
import ua.nure.illiashenko.mutuallearning.mapper.ConstraintViolationMapper;

@Slf4j
@Component
@AllArgsConstructor
public class UserValidator {

    @Autowired
    private final Validator validator;
    @Autowired
    private final ConstraintViolationMapper<RegistrationRequest> constraintViolationMapper;

    public void validateArticleRequest(RegistrationRequest registrationRequest){
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(registrationRequest);
        log.info(violations.toString());
        if (!violations.isEmpty()) {
            log.error("User wasn't created because of invalid data. Total mistake count: {}", violations.size());
            throw new ServiceApiException(constraintViolationMapper.map(violations), HttpStatus.BAD_REQUEST);
        }
    }

}
