package com.karl.myWebFlux.exception;

import lombok.Data;

@Data
public class CheckException extends RuntimeException{
    private String fieldName;
    private String fieldValue;

    public CheckException(String fieldName, String fieldValue){
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
