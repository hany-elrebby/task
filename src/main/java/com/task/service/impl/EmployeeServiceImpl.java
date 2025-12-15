package com.task.service.impl;

import com.task.service.EmployeeService;
import com.task.service.dto.EmployeeDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public Page<EmployeeDto> getAll(int size, int page) {
        return null;
    }

    @Override
    public EmployeeDto get(Long id) {
        return null;
    }
}
