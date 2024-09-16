package com.demo.demo.responseStatus;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


public class ResponseSuccess extends ResponseEntity<ResponseSuccess.Payload>{


    public ResponseSuccess(HttpStatusCode status, String message) {
        super(new Payload(status.value(),message), HttpStatus.OK);
    }

    public ResponseSuccess(HttpStatusCode status, String message,Object data) {
        super(new Payload(status.value(),message,data), HttpStatus.OK);
    }


    @Setter
    @Getter
    public static class Payload {
        private int status;
        private String message;
        private Object data;

        public Payload(int status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public Payload(int status, String message) {
            this.status = status;
            this.message = message;

        }

    }


}
