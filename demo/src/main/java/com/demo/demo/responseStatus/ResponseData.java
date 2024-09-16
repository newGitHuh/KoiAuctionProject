package com.demo.demo.responseStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
public class ResponseData<T> {
    private final int statusCode;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    //get - post
    public ResponseData(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    //put - patch - delete
    public ResponseData(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
