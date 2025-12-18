package com.task.service.error;

public class DataIntegrityException extends RuntimeException {
  public DataIntegrityException(String message) {
    super(message);
  }
}