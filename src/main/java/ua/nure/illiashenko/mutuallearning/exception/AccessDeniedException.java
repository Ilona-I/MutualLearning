package ua.nure.illiashenko.mutuallearning.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ServiceApiException{

    public AccessDeniedException(String errorCode) {
        super(errorCode, HttpStatus.FORBIDDEN);
    }
}
