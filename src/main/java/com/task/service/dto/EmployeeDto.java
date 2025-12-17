package com.task.service.dto;


import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record EmployeeDto(Integer id, String code,
                          String name,
                          LocalDate dateOfBirth,
                          AddressDto addressDto,
                          String mobile,
                          Double salary,
                          Long departmentId,
                          byte[] image) {
}
