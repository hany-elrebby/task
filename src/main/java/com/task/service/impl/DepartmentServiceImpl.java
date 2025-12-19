package com.task.service.impl;

import com.task.entity.Department;
import com.task.repository.DepartmentRepository;
import com.task.service.DepartmentService;
import com.task.service.dto.DepartmentDto;
import com.task.service.error.EntityNotFoundException;
import com.task.service.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public DepartmentDto save(DepartmentDto dto) {
        log.info("Creating department [code={}, name={}]", dto.code(), dto.name());

        departmentRepository.findByCode(dto.code())
                .ifPresent(d -> {
                    log.warn("Duplicate department code [code={}]", dto.code());
                    throw new RuntimeException("Department code already exists");
                });

        Department department = Department.builder()
                .name(dto.name())
                .code(dto.code())
                .description(dto.description())
                .build();

        Department saved = departmentRepository.save(department);

        log.info("Department created successfully [id={}, code={}]",
                saved.getId(), saved.getCode());

        return departmentMapper.toDto(saved);
    }

    @Override
    @Transactional
    public DepartmentDto update(Long id, DepartmentDto dto) {
        log.info("Updating department [id={}]", id);

        Department existed = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Department not found [id={}]", id);
                    return new EntityNotFoundException("Department", "id", id);
                });

        departmentRepository.findByCode(dto.code())
                .filter(d -> !d.getId().equals(id))
                .ifPresent(d -> {
                    log.warn("Duplicate department code [code={}]", dto.code());
                    throw new RuntimeException("Department code already exists");
                });

        log.debug("Updating department fields [id={}]", id);

        existed.setName(dto.name());
        existed.setCode(dto.code());
        existed.setDescription(dto.description());

        Department updated = departmentRepository.save(existed);

        log.info("Department updated successfully [id={}, code={}]",
                updated.getId(), updated.getCode());

        return departmentMapper.toDto(updated);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting department [id={}]", id);

        if (!departmentRepository.existsById(id)) {
            log.warn("Department not found for deletion [id={}]", id);
            throw new EntityNotFoundException("Department", "id", id);
        }

        departmentRepository.deleteById(id);

        log.info("Department deleted successfully [id={}]", id);
    }

    @Override
    public List<DepartmentDto> getAll() {
        log.debug("Fetching all departments");

        List<Department> departments = departmentRepository.findAll();

        log.debug("Found {} departments", departments.size());

        return departmentMapper.toDtoList(departments);
    }

    @Override
    public DepartmentDto get(Long id) {

        log.debug("Fetching department [id={}]", id);

        return departmentMapper.toDto(
                departmentRepository.findById(id)
                        .orElseThrow(() -> {
                            log.warn("Department not found [id={}]", id);
                            return new EntityNotFoundException("Department", "id", id);
                        })
        );
    }
}
