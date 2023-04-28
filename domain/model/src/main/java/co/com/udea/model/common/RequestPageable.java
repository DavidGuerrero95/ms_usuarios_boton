package co.com.udea.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class RequestPageable {

    @Min(value = 0, message = "El menor de page valor debe ser 0")
    private Integer page;
    @Min(value = 0, message = "El menor de size valor debe ser 0")
    @Max(value = 50, message = "El mayo valor de size debe ser 50")
    private Integer size;
    private String filter;
    private Boolean order;

}
