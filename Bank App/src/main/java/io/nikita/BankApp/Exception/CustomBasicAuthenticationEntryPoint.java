package io.nikita.BankApp.Exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String message = (authException != null && authException.getMessage() != null) ? authException.getMessage() : "Your Authentication Failed, Please check Credentials";
        String path = request.getRequestURI();
        response.setHeader("status-reason-test", " Authentication failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        //custom response
        String jsonErrorResponse = String.format(
                "{\"timestamp\":\"%s\",\"status\":%s,\"error\":\"%s\",\"message\":\"%s\",\"path\":\"%s\"}", currentDateTime, HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase(),message, path);
        response.getWriter().write(jsonErrorResponse);
    }
}