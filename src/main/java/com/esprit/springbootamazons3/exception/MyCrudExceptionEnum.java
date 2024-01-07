package com.esprit.springbootamazons3.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MyCrudExceptionEnum {
    PHARMACY_NOT_FOUND ("PHARMACY_NOT_FOUND", HttpStatus.NOT_FOUND);

    private final String code;
    private final HttpStatus httpStatus;

}