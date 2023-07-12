package com.Trakya.OrtayaKarisik.exception;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalBaseExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Map<String, String>> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, String>> handleAllException(
            Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, String>> handleMethodArgumentNotValidEx(
            MethodArgumentNotValidException ex, WebRequest request) {
        return getMapResponseEntity(ex);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Map<String, String>> handleConstraintViolationEx(
            MethodArgumentNotValidException ex, WebRequest request) {
        return getMapResponseEntity(ex);
    }

    private ResponseEntity<Map<String, String>> getMapResponseEntity(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        x -> {
                            String fieldName = ((FieldError) x).getField();
                            String errorMessage = x.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
