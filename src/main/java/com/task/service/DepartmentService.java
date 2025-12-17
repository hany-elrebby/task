package com.task.service;

import com.task.service.dto.DepartmentDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    DepartmentDto save(DepartmentDto departmentDto);
    DepartmentDto update(Long id, DepartmentDto departmentDto);
    Void delete(Long id);
    List<DepartmentDto> getAll();
    DepartmentDto get(Long id);
}
