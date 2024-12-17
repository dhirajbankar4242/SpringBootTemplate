package com.prime.app.config.exceptions;

import com.prime.app.config.GlobalSetting;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<Object> handleApplicationException(ApplicationException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(NoRecordFoundException.class)
    protected ResponseEntity<Object> handleNoRecordFoundException(NoRecordFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }


    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleException(NullPointerException ex) {
        log.error("java.lang.NullPointerException:{}", ex.getMessage(), ex);
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, GlobalSetting.COMMON_ERROR + ":" + ex.getLocalizedMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, "Incorrect data value Provided."));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error(ex.getMessage(), ex);

        StringBuilder builder = new StringBuilder("Mentod Argument Not Valid:");
        BindingResult result = ex.getBindingResult();
        for (FieldError fieldError : result.getFieldErrors()) {
            builder.append(validateName(fieldError.getField()));
            builder.append("-");
            builder.append(fieldError.getDefaultMessage());
        }
        builder.append(";");
        for (ObjectError fieldError : result.getGlobalErrors()) {
            builder.append(fieldError.getDefaultMessage());
        }
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, builder.toString()));
    }

    private String validateName(String field) {

        if (field.length() > 2) {
            field = field.replace("id", "");
        }
        return field;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, GlobalSetting.COMMON_ERROR));
    }
}
