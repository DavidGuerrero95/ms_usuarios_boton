package co.com.udea.model.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;
    
    public ResponseException(HttpStatus status) {
        super(status);
    }

    public ResponseException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public ResponseException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    public ResponseException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }
}
