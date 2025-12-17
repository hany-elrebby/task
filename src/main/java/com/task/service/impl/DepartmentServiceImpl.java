package com.task.service.impl;

import com.task.entity.Department;
import com.task.repository.DepartmentRepository;
import com.task.service.DepartmentService;
import com.task.service.dto.DepartmentDto;
import com.task.service.error.EntityNotFoundException;
import com.task.service.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = Department.builder()
                .name(departmentDto.name())
                .code(departmentDto.code())
                .build();
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    @Override
    public DepartmentDto update(Long id, DepartmentDto departmentDto) {
        Department existed = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department", "id", id));

        existed = Department.builder()
                .name(departmentDto.name())
                .code(departmentDto.code())
                .build();
        return departmentMapper.toDto(departmentRepository.save(existed));
    }

    @Override
    public Void delete(Long id) {
        return null;
    }

    @Override
    public List<DepartmentDto> getAll() {
        return departmentMapper.toDtoList(departmentRepository.findAll());
    }

    @Override
    public DepartmentDto get(Long id) {
        return departmentMapper.toDto(departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department", "id", id)));
    }
}
