package ua.nure.illiashenko.mutuallearning.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.dto.mark.CreateMarkRequest;
import ua.nure.illiashenko.mutuallearning.dto.mark.UpdateMarkRequest;
import ua.nure.illiashenko.mutuallearning.exception.mark.MarkValidationException;
import ua.nure.illiashenko.mutuallearning.mapper.ConstraintViolationMapper;

@Slf4j
@Component
@AllArgsConstructor
public class MarkValidator {

    @Autowired
    private final Validator validator;
    @Autowired
    private final ConstraintViolationMapper<CreateMarkRequest> constraintViolationMapperCreateMarkRequest;
    @Autowired
    private final ConstraintViolationMapper<UpdateMarkRequest> constraintViolationMapperUpdateMarkRequest;

    public void validateCreateMarkRequest(CreateMarkRequest createMarkRequest) {
        Set<ConstraintViolation<CreateMarkRequest>> violations = validator.validate(createMarkRequest);
        if (!violations.isEmpty()) {
            log.error("Mark wasn't saved because of invalid data. Total mistake count: {}", violations.size());
            throw new MarkValidationException(constraintViolationMapperCreateMarkRequest.map(violations));
        }
    }

    public void validateUpdateMarkRequest(UpdateMarkRequest updateMarkRequest) {
        Set<ConstraintViolation<UpdateMarkRequest>> violations = validator.validate(updateMarkRequest);
        if (!violations.isEmpty()) {
            log.error("Mark wasn't saved because of invalid data. Total mistake count: {}", violations.size());
            throw new MarkValidationException(constraintViolationMapperUpdateMarkRequest.map(violations));
        }
    }
}
