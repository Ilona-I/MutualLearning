package ua.nure.illiashenko.mutuallearning.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import org.springframework.stereotype.Component;

@Component
public class ConstraintViolationMapper<T> {

    public Map<String, String> map(Set<ConstraintViolation<T>> violations) {
        Map<String, String> errorDetails = new HashMap<>();
        for (ConstraintViolation<T> violation : violations) {
            String key = null;
            for (Path.Node node : violation.getPropertyPath()) {
                key = node.getName();
            }
            if (key == null) {
                key = violation.getConstraintDescriptor()
                    .getAnnotation()
                    .annotationType()
                    .getSimpleName();
            }
            String value = violation.getMessage();
            errorDetails.put(key, value);
        }
        return errorDetails;
    }

    public String toString(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(" "));
    }
}