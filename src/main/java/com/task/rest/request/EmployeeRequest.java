package com.task.rest.request;

import com.task.service.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public record EmployeeRequest(Integer id, String code,
                             String name,
                             LocalDate dateOfBirth,
                             AddressDto addressDto,
                             String mobile,
                             Double salary,
                             Long departmentId,
                             byte[] image) { }
