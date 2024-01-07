package com.esprit.springbootamazons3.exception;

import lombok.Getter;

@Getter
public class MyCrudException extends RuntimeException{
    private final MyCrudExceptionEnum myCrudExceptionEnum;

    public MyCrudException(MyCrudExceptionEnum myCrudExceptionEnum, Throwable exception, String message) {
        super(String.format(message), exception);
        this.myCrudExceptionEnum = myCrudExceptionEnum;
    }

    public MyCrudException(MyCrudExceptionEnum myCrudExceptionEnum,  String message) {
        super(String.format(message));
        this.myCrudExceptionEnum = myCrudExceptionEnum;
    }
}
