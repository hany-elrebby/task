package com.task.rest;

import com.task.entity.Employee;
import com.task.rest.request.EmployeeRequest;
import com.task.service.EmployeeService;
import com.task.service.dto.EmployeeDto;
import com.task.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeRest {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestPart EmployeeRequest employee,
            @RequestPart MultipartFile image) throws IOException {
        EmployeeDto saved = employeeService.save(employee, image);
        return new ResponseEntity<>(saved.id(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @RequestPart EmployeeRequest employee,
                                       @RequestPart(value = "image", required = false) MultipartFile image) {
        return ResponseEntity.ok(employeeService.update(id, employee, image).id());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAll(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(employeeService.getAll(size, page));
    }
}
