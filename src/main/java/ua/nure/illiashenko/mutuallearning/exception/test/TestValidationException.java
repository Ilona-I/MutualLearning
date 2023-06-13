package ua.nure.illiashenko.mutuallearning.exception.test;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class TestValidationException extends ServiceApiException {

    public TestValidationException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
