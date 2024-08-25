package com.carriyo.carriyodemo.controller.exception_handler;

import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.kms.model.NotFoundException;
import com.carriyo.carriyodemo.controller.model.response.ErrorData;
import com.carriyo.carriyodemo.controller.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleValidationExceptions(MethodArgumentNotValidException exception) {
        System.out.println("controller advice");
        var errorCode = "";
        ErrorData errorResponse = new ErrorData(exception.getFieldError().getDefaultMessage(),errorCode);
        return new Response(errorResponse);
    }


    @ExceptionHandler({ValidationException.class,NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleCustomValidationException(Exception exception) {
        System.out.println("Controller Advice: ValidationException");
        String errorCode = "VALIDATION_ERROR";
        ErrorData errorResponse = new ErrorData(exception.getMessage(), errorCode);
        return new Response(errorResponse);
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNotFoundException(NotFoundException exception) {
        System.out.println("Controller Advice: NotFoundException");
        String errorCode = "SHIPMENT_NOT_FOUND";
        ErrorData errorResponse = new ErrorData(exception.getMessage(), errorCode);
        return new Response(errorResponse);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleInternalServerErrorException(InternalServerErrorException exception) {
        System.out.println("Controller Advice: InternalServerErrorException");
        String errorCode = "INTERNAL_SERVER_ERROR";
        ErrorData errorResponse = new ErrorData(exception.getMessage(), errorCode);
        return new Response(errorResponse);
    }
}
