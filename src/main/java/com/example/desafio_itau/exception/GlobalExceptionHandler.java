package com.example.desafio_itau.exception;

import com.example.desafio_itau.service.exceptions.UnprocessableEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<Void> unprocessableEntity(UnprocessableEntity e) {
        return ResponseEntity.unprocessableEntity().build();
    }
}
