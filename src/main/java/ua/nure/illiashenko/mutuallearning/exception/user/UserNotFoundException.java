package ua.nure.illiashenko.mutuallearning.exception.user;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class UserNotFoundException  extends ServiceApiException {

    public UserNotFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }

}
