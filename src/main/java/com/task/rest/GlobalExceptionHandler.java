package com.task.rest;

import com.task.service.error.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getServletPath(), HttpStatus.NOT_FOUND, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(request.getServletPath(), HttpStatus.NOT_FOUND, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
