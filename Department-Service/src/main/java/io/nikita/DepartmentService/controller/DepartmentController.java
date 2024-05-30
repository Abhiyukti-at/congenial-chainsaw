package io.nikita.DepartmentService.controller;

import io.nikita.DepartmentService.Department;
import io.nikita.DepartmentService.exception.DepartmentNotFound;
import io.nikita.DepartmentService.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService service;
    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentDetails(@PathVariable Long id) {
        Optional<Department> department = service.getDepartmentById(id);
        log.info("Inside department details");
        if (department.isPresent()) {
            return ResponseEntity.ok(department.get());
        }else {
            throw new DepartmentNotFound("No Department Found with the id");
        }
    }
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department savedDepartment = service.createDepartment(department);
        log.info("Created Successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }


}