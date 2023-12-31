package com.spring.academy.exceptionhandling.exception;

public class RecordExistingException extends RuntimeException{

    public RecordExistingException(String message) {
        super(message);
    }
}
