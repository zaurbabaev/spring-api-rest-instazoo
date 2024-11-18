package com.example.instazoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CommentNotFoundException.class)
    public ExceptionBody handlerCommentNotFound(CommentNotFoundException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PostNotFoundException.class)
    public ExceptionBody handlerPostNotFound(PostNotFoundException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ExceptionBody handlerPostNotFound(UsernameNotFoundException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageNotFoundException.class)
    public ExceptionBody handlerImageNotFound(ImageNotFoundException ex) {
        return new ExceptionBody(ex.getMessage());
    }


}
