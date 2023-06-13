package ua.nure.illiashenko.mutuallearning.exception.user;

import java.util.List;
import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class UserValidationException extends ServiceApiException {

    public UserValidationException(List<String> errorDetails) {
        super(errorDetails, HttpStatus.BAD_REQUEST);
    }
}