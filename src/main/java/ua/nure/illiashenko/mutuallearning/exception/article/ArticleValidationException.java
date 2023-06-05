package ua.nure.illiashenko.mutuallearning.exception.article;

import java.util.List;
import java.util.Map;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import ua.nure.illiashenko.mutuallearning.exception.ServiceApiException;

@EqualsAndHashCode(callSuper = true)
public class ArticleValidationException extends ServiceApiException {

        public ArticleValidationException(List<String> errorDetails) {
                super(errorDetails, HttpStatus.BAD_REQUEST);
        }
}
