package com.task.entity;

import com.task.service.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends AbstractMapper<Department, DepartmentDto> {
}
