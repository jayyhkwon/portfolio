package com.callbus.kyh.error;

/**
 * Created by yhkwon on 2020/05/20
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String error) {
        super(error);
    }
}
