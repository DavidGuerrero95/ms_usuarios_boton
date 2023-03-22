package co.com.udea.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class ResponseData <T>{
    private T data;
    private int status;
    private String message;
    private String error;

    public ResponseData(T data, int status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
