package com.spring.academy.exceptionhandling.exception;

public class InternalServerExceptionHandler extends RuntimeException{

    public InternalServerExceptionHandler(String message) {
        super(message);
    }
}
