package com.task.entity;

import com.task.service.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {

    Employee toEntity(EmployeeDto employeeDto);
    @Mapping()
    EmployeeDto toDto(Employee employee);
}
