package com.task.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String path;
    private HttpStatus status;
    private String error;
    private LocalDateTime timestamp;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String path, HttpStatus status, Throwable th) {
        this();
        this.message = th.getMessage();
        this.path = path;
        this.status = status;
        this.error = th.toString();
    }
}