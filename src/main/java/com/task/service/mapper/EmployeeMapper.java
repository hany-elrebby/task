package com.task.service.mapper;

import com.task.entity.Employee;
import com.task.entity.Image;
import com.task.service.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ImageMapper.class)
public interface EmployeeMapper extends AbstractMapper<Employee, EmployeeDto> {

    @Mapping(source = "image", target = "image")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "addressDto", target = "address")
    Employee toEntity(EmployeeDto employeeDto);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "address", target = "addressDto")
    EmployeeDto toDto(Employee employee);
}
