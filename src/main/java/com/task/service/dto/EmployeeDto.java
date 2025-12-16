package com.task.service.dto;


import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record EmployeeDto(Integer id, String Code,
                          String Name,
                          LocalDate dateOfBirth,
                          AddressDto addressDto,
                          String Mobile,
                          Double salary,
                          Long departmentId,
                          byte[] image) {
}
