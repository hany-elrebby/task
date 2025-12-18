package com.task.rest.request;

import com.task.entity.Department;
import com.task.service.DepartmentService;
import com.task.service.dto.DepartmentDto;
import com.task.service.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentRest {
    private final DepartmentService departmentService;


    @PostMapping
    public ResponseEntity<Long> create(@RequestBody DepartmentDto departmentDto) {
        departmentDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(departmentDto.id(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getAll(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
