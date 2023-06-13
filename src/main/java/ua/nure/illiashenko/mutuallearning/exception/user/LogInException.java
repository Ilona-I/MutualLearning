package ua.nure.illiashenko.mutuallearning.exception.user;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class LogInException extends ServiceApiException {

    public LogInException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
