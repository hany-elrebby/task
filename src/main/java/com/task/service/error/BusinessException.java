package com.task.service.error;

public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }
}