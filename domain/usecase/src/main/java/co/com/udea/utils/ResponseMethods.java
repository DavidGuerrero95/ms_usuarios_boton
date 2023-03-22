package co.com.udea.utils;

import co.com.udea.model.common.ResponseData;
import co.com.udea.model.common.exceptions.ServiceException;
import co.com.udea.model.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

@Slf4j
public class ResponseMethods {

    public static ResponseData getSuccessResponseData(Object data, HttpStatus status, String message) {
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setStatus(status.value());
        responseData.setMessage(message);
        return responseData;
    }

    public static ResponseData getSuccessResponseData(Object data, HttpStatus status) {
        ResponseData responseData = new ResponseData();
        responseData.setData(data);
        responseData.setStatus(status.value());
        return responseData;
    }

    public static ResponseData getSuccessResponseData(HttpStatus status, String message) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus(status.value());
        responseData.setMessage(message);
        return responseData;
    }

    public static ResponseData getErrorResponseData(HttpStatus status, String message) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus(status.value());
        responseData.setError(message);
        return responseData;
    }

    public static ResponseData getErrorResponseData(ServiceException e, String message) {
        log.error(message + Constants.PUNTOS + e.getMessage() + Constants.LOCALIZACION + e.getLocalizedMessage());
        return getErrorResponseData(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static ResponseData getErrorResponseData(DataAccessException e, HttpStatus httpStatus, String message) {
        log.error(message + Constants.PUNTOS + e.getMessage() + Constants.LOCALIZACION + e.getLocalizedMessage());
        return getErrorResponseData(httpStatus, message);
    }

    public static ResponseData getErrorResponseData(ServiceException e, HttpStatus httpStatus, String message) {
        log.error(message + Constants.PUNTOS + e.getMessage() + Constants.LOCALIZACION + e.getLocalizedMessage());
        return getErrorResponseData(httpStatus, message);
    }
}