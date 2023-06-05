package ua.nure.illiashenko.mutuallearning.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import org.springframework.stereotype.Component;

@Component
public class ConstraintViolationMapper<T> {

    public List<String> map(Set<ConstraintViolation<T>> violations) {
        List<String>  errorDetails = new ArrayList<>();
        for (ConstraintViolation<T> violation : violations) {
            String value = violation.getMessage();
            errorDetails.add(value);
        }
        return errorDetails;
    }
}