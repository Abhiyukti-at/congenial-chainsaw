package io.nikita.security.securityMS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter@Setter@AllArgsConstructor
public class ErrorResponse {
    HttpStatus httpStatus;
    String message;
}