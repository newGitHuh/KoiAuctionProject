package com.demo.demo.error;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;

@RestControllerAdvice

public class RestHandlerException {

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class, NoResourceFoundException.class,Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidException(Exception ex,WebRequest request) {
            ErrorResponse error = new ErrorResponse();
            error.setTimeStamp(new Date());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            error.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
            error.setStackTrace(request.getDescription(false).replace("uri=",""));
            error.setException(ex.getMessage());

            return error;
    }
}
