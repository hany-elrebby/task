package com.task.service;

import com.task.service.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeDto save(EmployeeDto employeeDto);
    EmployeeDto update(Long id, EmployeeDto employeeDto);
    Void delete(Long id);
    Page<EmployeeDto> getAll(int size, int page);
    EmployeeDto get(Long id);
}
