package ua.nure.illiashenko.mutuallearning.exception.user;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class ChangePasswordException extends ServiceApiException {

    public ChangePasswordException() {
        super(HttpStatus.BAD_REQUEST);
    }
}