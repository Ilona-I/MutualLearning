package ua.nure.illiashenko.mutuallearning.exception;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceApiException extends RuntimeException {

    @NonNull
    protected final String errorCode;
    protected Map<String, String> errorDetails;
    protected HttpStatus httpStatus;

    public ServiceApiException(String errorCode, Map<String, String> errorDetails, HttpStatus httpStatus){
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
        this.httpStatus = httpStatus;
    }
    public ServiceApiException(String errorCode, HttpStatus httpStatus){
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}

