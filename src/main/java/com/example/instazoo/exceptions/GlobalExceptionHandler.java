package com.example.instazoo.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserExistException.class)
    public ExceptionBody handleUserExist(UserExistException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleResourceNotFound(ResourceNotFoundException ex) {
        return new ExceptionBody(ex.getMessage());
    }

//    @ExceptionHandler(IllegalStateException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ExceptionBody handelIllegalState(IllegalStateException ex) {
//        return new ExceptionBody(ex.getMessage());
//    }

    @ExceptionHandler({AccessDeniedException.class, org.springframework.security.access.AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionBody handleAccessDenied(AccessDeniedException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handelMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ExceptionBody exceptionBody = new ExceptionBody("Valid failed");
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        exceptionBody.setErrors(errors.stream()
                .collect(Collectors.toMap(
                        FieldError::getField, FieldError::getDefaultMessage,
                        (existingMessage, newMessage) -> existingMessage + " " + newMessage)
                ));
        return exceptionBody;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleConstraintViolation(ConstraintViolationException ex) {
        ExceptionBody exceptionBody = new ExceptionBody("Validation failed");
        exceptionBody.setErrors(ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage)
                ));
        return exceptionBody;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleAuthentication(AuthenticationException ex) {
        return new ExceptionBody(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleException(Exception ex) {
        return new ExceptionBody(ex.getMessage());
    }



}
