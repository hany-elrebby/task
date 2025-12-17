package com.task.rest;

import com.task.entity.Employee;
import com.task.rest.request.EmployeeRequest;
import com.task.service.EmployeeService;
import com.task.service.dto.EmployeeDto;
import com.task.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeRest {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestPart EmployeeRequest employee,
            @RequestPart MultipartFile image) throws IOException {
        EmployeeDto saved = employeeService.save(employee, image);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.id())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.update(id, employeeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDto>> getAll(
                                                    @RequestParam int size,
                                                    @RequestParam int page) {
        return ResponseEntity.ok(employeeService.getAll(size, page));
    }
}
