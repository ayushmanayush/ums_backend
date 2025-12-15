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

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<?> handelTeacherNotFound(TeacherNotFoundException ex){
        HashMap<String,Object> map = new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("Ststus",HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(map);
    }
    @ExceptionHandler(DepartmentNotFound.class)
    public ResponseEntity<?> handleDepartmentNotFound(DepartmentNotFound ex){
        HashMap<String,Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("Status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(map);
    }
    @ExceptionHandler(SectionNotFound.class)
    public ResponseEntity<?> helperSectionNotFound(SectionNotFound ex){
        HashMap<String,Object> map = new HashMap<>();
        map.put("message" , ex.getMessage());
        map.put("status",HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);

    }
    @ExceptionHandler(MappingAlreadyExists.class)
    public ResponseEntity<?> helpermappingalreadyexists(MappingAlreadyExists ex){
        HashMap<String,Object> map = new HashMap<>();
        map.put("message" , ex.getMessage());
        map.put("status",HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
