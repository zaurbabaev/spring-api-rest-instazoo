package com.example.instazoo.exceptions;

public class UserExistException extends RuntimeException {

    public UserExistException(String message) {
        super(message);
    }
}
