package com.task.service.impl;

import com.task.entity.Address;
import com.task.entity.Department;
import com.task.entity.Employee;
import com.task.entity.Image;
import com.task.repository.DepartmentRepository;
import com.task.repository.EmployeeRepository;
import com.task.rest.request.EmployeeRequest;
import com.task.service.EmployeeService;
import com.task.service.dto.EmployeeDto;
import com.task.service.error.EntityNotFoundException;
import com.task.service.mapper.AddressMapper;
import com.task.service.mapper.EmployeeMapper;
import com.task.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private AddressMapper addressMapper;
    private ImageMapper imageMapper;
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto save(EmployeeRequest employeeRequest, MultipartFile multipartFile) {
        Department department = departmentRepository.findById(employeeRequest.departmentId())
                .orElseThrow(() -> new EntityNotFoundException("department", "id", employeeRequest.departmentId()));

        Address address = Address
                .builder()
                .country("Egypt")
                .state(employeeRequest.addressDto().state())
                .city(employeeRequest.addressDto().city())
                .street(employeeRequest.addressDto().street())
                .postalCode(employeeRequest.addressDto().postalCode())
                .build();

        Employee employee = Employee
                .builder()
                .Name(employeeRequest.Name())
                .department(department)
                .address(address)
                .dateOfBirth(employeeRequest.dateOfBirth())
                .Mobile(employeeRequest.Mobile())
                .image(image(multipartFile))
                .build();

        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    public Image image(MultipartFile file)  {
        try {
            return imageMapper.toEntity(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Image con not be saved");
        }
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
