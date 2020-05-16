package com.callbus.kyh.error;

public class DuplicatedPhoneNumberException extends RuntimeException {

    public DuplicatedPhoneNumberException(String error) {
        super(error);
    }
}
