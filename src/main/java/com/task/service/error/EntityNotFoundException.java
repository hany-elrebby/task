package com.task.service.error;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, String propertyName, Object value) {
        super(entity + " with " + propertyName + " " + value.toString() + " is not found");
    }
}
