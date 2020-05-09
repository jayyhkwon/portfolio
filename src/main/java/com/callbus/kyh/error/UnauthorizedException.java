package com.callbus.kyh.error;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String error) {
        super(error);
    }
}
