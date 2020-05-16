package com.callbus.kyh.error;

public class UnknownException extends RuntimeException {

    public UnknownException(String error) {
        super(error);
    }
}
