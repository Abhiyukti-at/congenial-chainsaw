package io.nikita.BankApp.Exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String message = (accessDeniedException != null && accessDeniedException.getMessage() != null) ? accessDeniedException.getMessage() : "Unauthorized Access";
        String path = request.getRequestURI();
        response.setHeader("reason-access-denied", " Authentication failed");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");
        //custom response
        String jsonErrorResponse = String.format(
                "{\"timestamp\":\"%s\",\"status\":%s,\"error\":\"%s\",\"message\":\"%s\",\"path\":\"%s\"}", currentDateTime, HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.getReasonPhrase(),message, path);
        response.getWriter().write(jsonErrorResponse);
    }
}