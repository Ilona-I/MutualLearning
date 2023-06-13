package ua.nure.illiashenko.mutuallearning.exception.mark;

import java.util.List;
import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class MarkValidationException extends ServiceApiException {

    public MarkValidationException(List<String> errorDetails) {
        super(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
