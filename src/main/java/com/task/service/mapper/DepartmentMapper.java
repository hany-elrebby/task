package com.task.service.mapper;

import com.task.entity.Department;
import com.task.service.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends AbstractMapper<Department, DepartmentDto> {
}
