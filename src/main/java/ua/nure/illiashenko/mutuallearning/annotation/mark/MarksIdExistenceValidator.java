package ua.nure.illiashenko.mutuallearning.annotation.mark;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;

@AllArgsConstructor
@Component
public class MarksIdExistenceValidator implements ConstraintValidator<MarksIdExistenceValidation, String[]> {

    @NonNull
    @Autowired
    private MarkRepository markRepository;

    @Override
    public boolean isValid(String[] marksId, ConstraintValidatorContext context) {
        for (String markId : marksId) {
            int markIdInt = Integer.parseInt(markId);
            if (!markRepository.existsById(markIdInt)) {
                return false;
            }
        }
        return true;
    }
}
