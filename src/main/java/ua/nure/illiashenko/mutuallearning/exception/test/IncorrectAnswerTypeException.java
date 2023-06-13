package ua.nure.illiashenko.mutuallearning.exception.test;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class IncorrectAnswerTypeException extends ServiceApiException {

    public IncorrectAnswerTypeException() {
        super(HttpStatus.BAD_REQUEST);
    }
}