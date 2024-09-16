package com.demo.demo.error;

import lombok.Data;

import java.util.Date;

@Data

public class ErrorResponse {
    private Date timeStamp;
    private int errorCode;
    private String message;
    private String stackTrace;
    private String exception;
}
