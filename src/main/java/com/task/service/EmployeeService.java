package com.task.service;

import com.task.entity.Employee;
import com.task.rest.request.EmployeeRequest;
import com.task.service.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface EmployeeService {
    EmployeeDto save(EmployeeRequest employeeRequest, MultipartFile multipartFile);
    EmployeeDto update(Long id, EmployeeRequest employeeRequest, MultipartFile multipartFile);
    Void delete(Long id);
    Page<EmployeeDto> getAll(int size, int page);
    EmployeeDto get(Long id);
}
