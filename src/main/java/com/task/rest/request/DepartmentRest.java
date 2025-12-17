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
    public ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.save(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAll() {
        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }
}
