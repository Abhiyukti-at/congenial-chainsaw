package io.nikita.EmpProject.controller;

import io.nikita.EmpProject.DepartmentClient;
import io.nikita.EmpProject.DepartmentDetails;
import io.nikita.EmpProject.Employee;
import io.nikita.EmpProject.EmployeeResponse;
import io.nikita.EmpProject.exception.DepartmentException;
import io.nikita.EmpProject.exception.EmployeeNotFound;
import io.nikita.EmpProject.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private RestTemplate restTemplate;
    private DepartmentClient departmentClient;
    private EmployeeService service;
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeDepartment(@PathVariable Long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
        log.info("Inside the getEmployee Department");
        Optional<Employee> employee= service.getEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
//        Employee savedEmployee = employeeRepository.save(employee);
        Employee savedEmployee = service.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable Long id) {
        log.info("Inside get Employee Details");
        Optional<Employee> employeeOptional = service.getEmployeeById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            ResponseEntity<DepartmentDetails> department= departmentClient.getDepartmentDetails(id);
            // Fetch department details from Department Service
//            ResponseEntity<DepartmentDetails> department = restTemplate.getForEntity("http://department-service/departments/{id}" ,DepartmentDetails.class,employee.getDeptId());
            if (department!= null) {
                EmployeeResponse employeeDepartmentDetails = new EmployeeResponse(employee.getEmpId(), employee.getEmail(),employee.getName(),department.getBody().getDeptId(), department.getBody().getName());
                return ResponseEntity.ok(employeeDepartmentDetails);
            } else {
                throw new DepartmentException("No Department Found with the id");
            }
        } else {
            throw new EmployeeNotFound("Employee Not Found");
        }
    }

}