package io.nikita.EmpProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentException extends RuntimeException{
    public DepartmentException(String message) {
        super(message);
    }
}