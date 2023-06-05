package ua.nure.illiashenko.mutuallearning.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.nure.illiashenko.mutuallearning.exception.AccessDeniedException;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;
import ua.nure.illiashenko.mutuallearning.exception.article.ArticleNotFoundException;
import ua.nure.illiashenko.mutuallearning.exception.article.ArticleValidationException;
import ua.nure.illiashenko.mutuallearning.exception.mark.MarkNotFoundException;
import ua.nure.illiashenko.mutuallearning.exception.user.UserNotFoundException;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({
        ArticleNotFoundException.class,
        ServiceApiException.class,
        ArticleValidationException.class,
        MarkNotFoundException.class,
        AccessDeniedException.class,
        UserNotFoundException.class})
    public ResponseEntity<List<String>> handleServiceApiException(ServiceApiException exception) {
        return ResponseEntity.status(exception.getHttpStatus())
            .body(exception.getErrorDetails());
    }
}
