package com.task.service.impl;

import com.task.entity.Address;
import com.task.entity.Department;
import com.task.entity.Employee;
import com.task.entity.Image;
import com.task.repository.AddressRepository;
import com.task.repository.DepartmentRepository;
import com.task.repository.EmployeeRepository;
import com.task.repository.specification.EmployeeSpecification;
import com.task.rest.request.EmployeeRequest;
import com.task.service.EmployeeService;
import com.task.service.dto.EmployeeDto;
import com.task.service.error.BusinessException;
import com.task.service.error.EntityNotFoundException;
import com.task.service.mapper.AddressMapper;
import com.task.service.mapper.EmployeeMapper;
import com.task.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final AddressMapper addressMapper;
    private final ImageMapper imageMapper;
    private final DepartmentRepository departmentRepository;
    private final EmployeeSpecification employeeSpecification;
    private final AddressRepository addressRepository;


    @Override
    @Transactional
    public EmployeeDto save(EmployeeRequest employeeRequest, MultipartFile multipartFile) {
        log.info("Creating new employee [code={}, name={}]", employeeRequest.code(), employeeRequest.name());

        Department department = departmentRepository.findById(employeeRequest.departmentId())
                .orElseThrow(() -> {
                    log.warn("Department not found [id={}]", employeeRequest.departmentId());
                    return new EntityNotFoundException("department", "id", employeeRequest.departmentId());
                });

        employeeRepository.findByCode(employeeRequest.code())
                .ifPresent(d ->  {
                    log.warn("Duplicate employee code detected [code={}]", employeeRequest.code());
                    throw new BusinessException("Error code duplicated");
                });

        Address address = Address
                .builder()
                .state(employeeRequest.addressDto().state())
                .city(employeeRequest.addressDto().city())
                .street(employeeRequest.addressDto().street())
                .build();

        Employee employee = Employee
                .builder()
                .code(employeeRequest.code())
                .name(employeeRequest.name())
                .department(department)
                .salary(employeeRequest.salary())
                .address(address)
                .dateOfBirth(employeeRequest.dateOfBirth())
                .mobile(employeeRequest.mobile())
                .image(buildImage(multipartFile))
                .build();
        Employee saved = employeeRepository.save(employee);

        log.info("Employee created successfully [id={}, code={}]", saved.getId(), saved.getCode());

        return employeeMapper.toDto(saved);
    }

    public Image buildImage(MultipartFile file)  {
        if (file == null || file.isEmpty()) {
            log.debug("No image provided for employee");
            return null;
        }

        log.debug("Processing image [originalName={}, size={} bytes]",
                file.getOriginalFilename(), file.getSize());

        try {
            return imageMapper.toEntity(file.getBytes());
        } catch (IOException e) {
            log.error("Failed to read image file", e);
            throw new RuntimeException("Image couldn't be saved");
        }
    }

    @Override
    @Transactional
    public EmployeeDto update(Long id, EmployeeRequest employeeRequest, MultipartFile multipartFile) {
        log.info("Updating employee [id={}]", id);

        Employee existed = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Employee not found [id={}]", id);
                    return new EntityNotFoundException("Employee", "id", id);
                });

        employeeRepository.findByCode(employeeRequest.code())
                .filter(e -> !e.getId().equals(id))
                .ifPresent(e -> {
                    log.warn("Duplicate employee code during update [code={}, employeeId={}]",
                            employeeRequest.code(), id);

                    throw new DataIntegrityViolationException("Employee code already exists");
                });

        Department department = departmentRepository.findById(employeeRequest.departmentId())
                .orElseThrow(() -> {
                    log.warn("Department not found [id={}]", employeeRequest.departmentId());
                    return new EntityNotFoundException("Department", "id", employeeRequest.departmentId());
                });

        Address address = addressRepository.findById(employeeRequest.addressDto().id())
                .orElseThrow(() -> {
                    log.warn("Address not found [id={}]", employeeRequest.addressDto().id());
                    throw new EntityNotFoundException("Address", "id", employeeRequest.addressDto().id());
                });

        address.setState(employeeRequest.addressDto().state());
        address.setCity(employeeRequest.addressDto().city());
        address.setStreet(employeeRequest.addressDto().street());

        existed.setCode(employeeRequest.code());
        existed.setName(employeeRequest.name());
        existed.setDepartment(department);
        existed.setAddress(address);
        existed.setSalary(employeeRequest.salary());
        existed.setDateOfBirth(employeeRequest.dateOfBirth());
        existed.setMobile(employeeRequest.mobile());

        existed.setImage(buildImage(multipartFile));

        Employee updated = employeeRepository.save(existed);

        log.info("Employee updated successfully [id={}, code={}]", updated.getId(), updated.getCode());

        return employeeMapper.toDto(updated);
    }

    @Override
    @Transactional
    public Void delete(Long id) {
        log.info("Deleting employee [id={}]", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Employee not found for deletion [id={}]", id);
                    return new EntityNotFoundException("Employee", "id", id);
                });

        employeeRepository.delete(employee);

        log.info("Employee deleted successfully [id={}]", id);

        return null;
    }

    @Override
    public Page<EmployeeDto> getAll(int size, int page) {
        log.debug("Fetching employees [page={}, size={}]", page, size);

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository.findAll(employeeSpecification.getAll(), pageable)
                .map(employeeMapper::toDto);
    }

    @Override
    public EmployeeDto get(Long id) {
        log.debug("Fetching employee [id={}]", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Employee not found [id={}]", id);
                    return new EntityNotFoundException("Employee", "id", id);
                });

        return employeeMapper.toDto(employee);
    }
}