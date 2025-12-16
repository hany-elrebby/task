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
    @Mapping(target = "addresses", expression =
    "java(employeeDto.getAddressDto() != null ? List.of(addressMapper.toEntity(employeeDto.getAddressDto())) : null)")
    Employee toEntity(EmployeeDto employeeDto);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "image", target = "image")
    @Mapping(target = "addressDto", expression =
    "java(employee.getAddresses() != null && !employee.getAddresses().isEmpty() ? addressMapper.toDto(employee.getAddresses().get(0)) : null)")
    EmployeeDto toDto(Employee employee);
}
