package io.nikita.DepartmentService.service;

import io.nikita.DepartmentService.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department createDepartment(Department department);
}