package com.project.catalogo.infrastruture.adapter.in.rest.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            IllegalArgumentException.class,
            EntityNotFoundException.class,
            BusinessException.class
    })
    public ResponseEntity<Map<String, String>> handleExceptions(Exception ex) {

        Map<String, String> response = new HashMap<>();

        String message;

        if (ex instanceof MethodArgumentNotValidException validationEx) {

            message = validationEx.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage() !=null ? error.getDefaultMessage() : "Error de validación")
                    .findFirst()
                    .orElse("Error de validación");

        } else {
            message = ex.getMessage();
        }

        response.put("message", message);

        return ResponseEntity.badRequest().body(response);
    }
}