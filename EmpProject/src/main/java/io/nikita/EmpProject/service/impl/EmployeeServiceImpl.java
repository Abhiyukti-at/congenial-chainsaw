package io.nikita.EmpProject.service.impl;

import io.nikita.EmpProject.Employee;
import io.nikita.EmpProject.EmployeeRepository;
import io.nikita.EmpProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}