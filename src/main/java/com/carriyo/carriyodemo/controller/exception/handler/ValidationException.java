package com.carriyo.carriyodemo.controller.exception.handler;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
