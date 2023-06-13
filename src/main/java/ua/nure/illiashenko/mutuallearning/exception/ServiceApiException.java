package ua.nure.illiashenko.mutuallearning.exception;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceApiException extends RuntimeException {

    protected List<String> errorDetails;
    protected HttpStatus httpStatus;

    public ServiceApiException(List<String> errorDetails, HttpStatus httpStatus){
        this.errorDetails = errorDetails;
        this.httpStatus = httpStatus;
    }

    public ServiceApiException(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
}

