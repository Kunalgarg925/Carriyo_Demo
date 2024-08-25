package com.carriyo.carriyodemo.controller.exception_handler;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
