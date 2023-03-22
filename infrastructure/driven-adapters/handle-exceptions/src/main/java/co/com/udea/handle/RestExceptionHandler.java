package co.com.udea.handle;

import co.com.udea.model.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RestExceptionHandler.Error handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                            WebRequest request) {
        BindingResult result = ex.getBindingResult();

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> {
            errorList.add(fieldError.getObjectName() + Constants.PUNTO + fieldError.getField() + Constants.PUNTOS
                    + fieldError.getDefaultMessage() + Constants.REJECTED_VALUE + fieldError.getRejectedValue()
                    + Constants.CORCHETES);
        });
        result.getGlobalErrors().forEach((fieldError) -> {
            errorList.add(fieldError.getObjectName() + Constants.PUNTOS + fieldError.getDefaultMessage());
        });

        return new RestExceptionHandler.Error(HttpStatus.BAD_REQUEST, errorList.get(errorList.size() - 1));
    }

    public static class Error {
        private int errorCode;
        private String error;
        private String errorMessage;

        public Error(HttpStatus status, String message) {
            this.errorCode = status.value();
            this.error = status.name();
            this.errorMessage = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

    }
}
