package ua.nure.illiashenko.mutuallearning.exception.article;

import java.util.Map;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

@EqualsAndHashCode(callSuper = true)
public class ArticleValidationException extends ServiceApiException {

        public ArticleValidationException(String errorCode, Map<String, String> errorDetails) {
                super(errorCode, errorDetails, HttpStatus.BAD_REQUEST);

        }
}
