package ua.nure.illiashenko.mutuallearning.exception;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ServiceApiException extends RuntimeException {

    @NonNull
    protected final String errorCode;
    @NonNull
    protected final Map<String, String> errorDetails;
    protected HttpStatus httpStatus;
}

