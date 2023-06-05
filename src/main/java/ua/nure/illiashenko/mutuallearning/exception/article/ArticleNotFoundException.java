package ua.nure.illiashenko.mutuallearning.exception.article;

import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class ArticleNotFoundException extends ServiceApiException {

    public ArticleNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
