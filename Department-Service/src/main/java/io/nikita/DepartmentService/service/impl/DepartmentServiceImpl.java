package io.nikita.DepartmentService.service.impl;

import io.nikita.DepartmentService.Department;
import io.nikita.DepartmentService.DepartmentRepository;
import io.nikita.DepartmentService.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository repository;

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        return repository.save(department);
    }
}