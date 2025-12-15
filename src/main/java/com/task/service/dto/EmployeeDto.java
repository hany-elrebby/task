package com.task.service.dto;


import java.time.LocalDate;

public record EmployeeDto(Integer id, String Code,
                          String Name,
                          LocalDate dateOfBirth,
                          AddressDto addressDto,
                          String Mobile,
                          Double salary,
                          DepartmentDto departmentDto,
                          ImageDto image) {
}
