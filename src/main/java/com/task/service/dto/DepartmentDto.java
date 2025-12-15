package com.task.service.dto;

import lombok.Builder;

@Builder
public record DepartmentDto(Long id, String name, String code) {
}
