package ua.nure.illiashenko.mutuallearning.exception.article;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class ArticleNotFoundException extends ServiceApiException {

    public ArticleNotFoundException(String errorCode) {
        super(errorCode, HttpStatus.NOT_FOUND);
    }
}
