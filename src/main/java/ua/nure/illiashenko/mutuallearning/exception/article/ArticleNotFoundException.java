package ua.nure.illiashenko.mutuallearning.exception.article;

import java.util.Map;
import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

public class ArticleNotFoundException extends ServiceApiException {

    public ArticleNotFoundException(String errorCode, Map<String, String> errorDetails) {
        super(errorCode, errorDetails, HttpStatus.NOT_FOUND);
    }
}
