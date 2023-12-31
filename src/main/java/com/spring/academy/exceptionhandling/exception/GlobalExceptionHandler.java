package com.spring.academy.exceptionhandling.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConfigDataResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleResourceNotFoundException(ConfigDataResourceNotFoundException e) {
        return new ResponseEntity<>("Resource not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordExistingException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<String> handleRecordExistingException(RecordExistingException e) {
        return new ResponseEntity<>("Record is existing: " + e.getMessage(), HttpStatus.FOUND);
    }

    @ExceptionHandler(InternalServerExceptionHandler.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerExceptionHandler(InternalServerExceptionHandler e) {
        return new ResponseEntity<>("Internal server exception : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
