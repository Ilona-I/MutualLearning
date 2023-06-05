package ua.nure.illiashenko.mutuallearning.exception.mark;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class MarkNotFoundException extends ServiceApiException {

    public MarkNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
