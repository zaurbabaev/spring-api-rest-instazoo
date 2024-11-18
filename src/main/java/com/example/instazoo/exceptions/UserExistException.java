package com.example.instazoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserExistException extends RuntimeException {

    public UserExistException(String message) {
        super(message);
    }
}
