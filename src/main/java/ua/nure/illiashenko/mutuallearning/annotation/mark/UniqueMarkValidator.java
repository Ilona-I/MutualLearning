package ua.nure.illiashenko.mutuallearning.annotation.mark;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.nure.illiashenko.mutuallearning.repository.MarkRepository;

@AllArgsConstructor
@Component
public class UniqueMarkValidator implements ConstraintValidator<UniqueMarkValidation, String> {

    @NonNull
    @Autowired
    private MarkRepository markRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return !markRepository.existsMarkByTitle(title);
    }
}

