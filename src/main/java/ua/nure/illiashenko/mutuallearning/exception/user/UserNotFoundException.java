package ua.nure.illiashenko.mutuallearning.exception.user;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class UserNotFoundException  extends ServiceApiException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
