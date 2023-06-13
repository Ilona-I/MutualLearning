package ua.nure.illiashenko.mutuallearning.exception.comment;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class CommentValidationException extends ServiceApiException {

    public CommentValidationException() {
        super(HttpStatus.BAD_REQUEST);
    }
}

