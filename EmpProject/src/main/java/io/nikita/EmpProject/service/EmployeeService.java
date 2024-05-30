package io.nikita.EmpProject.service;

import io.nikita.EmpProject.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Optional<Employee> getEmployeeById(Long id);
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
}