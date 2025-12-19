package com.task.service.error;

import com.task.rest.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        log.error("EntityNotFound exception occurred", e);

        ErrorResponse errorResponse = new ErrorResponse(request.getServletPath(), HttpStatus.OK, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleDateIntegrityException(BusinessException e, HttpServletRequest request) {
        log.error("DataIntegrity exception occurred", e);

        ErrorResponse errorResponse = new ErrorResponse(request.getServletPath(), HttpStatus.OK, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("Runtime Exception", e);

        ErrorResponse errorResponse = new ErrorResponse(request.getServletPath(),
                HttpStatus.OK, e);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);

    }

}
