package com.carriyo.carriyodemo.controller.exception.handler;

import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.kms.model.NotFoundException;
import com.carriyo.carriyodemo.controller.model.response.ErrorResponse;
import com.carriyo.carriyodemo.controller.model.response.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody handleValidationExceptions(MethodArgumentNotValidException exception){
        var errorCode = "";
        ErrorResponse errorResponse = new ErrorResponse(exception.getFieldError().getDefaultMessage(),errorCode);
        return new ResponseBody(errorResponse);
    }


    @ExceptionHandler({ValidationException.class,NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBody handleCustomValidationException(Exception exception){
        String errorCode = "VALIDATION_ERROR";
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), errorCode);
        return new ResponseBody(errorResponse);
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseBody handleNotFoundException(NotFoundException exception){
        String errorCode = "SHIPMENT_NOT_FOUND";
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), errorCode);
        return new ResponseBody(errorResponse);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBody handleInternalServerErrorException(InternalServerErrorException exception){
        String errorCode = "INTERNAL_SERVER_ERROR";
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), errorCode);
        return new ResponseBody(errorResponse);
    }
}
