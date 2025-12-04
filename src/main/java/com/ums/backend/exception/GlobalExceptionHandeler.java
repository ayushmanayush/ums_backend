package com.ums.backend.exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeler {
    @ExceptionHandler(StudentnotFound.class)
    public ResponseEntity<?> handelStudentNotFound(StudentnotFound ex){
        HashMap<String, Object> map = new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("Status",HttpStatus.NOT_FOUND.value());
        map.put("TimeStamp",LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
