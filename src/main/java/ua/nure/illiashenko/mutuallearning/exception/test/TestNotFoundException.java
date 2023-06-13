package ua.nure.illiashenko.mutuallearning.exception.test;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class TestNotFoundException extends ServiceApiException {

    public TestNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
