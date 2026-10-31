package com.book_catalog_web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class Exception extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 2144828128032149004L;

    public Exception(String message) {
        super(message);
    }
}
