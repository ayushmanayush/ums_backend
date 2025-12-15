package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.TeacherAssignmentRequestDto;
import com.ums.backend.service.TeacherAssignmentService;

@RestController
@RequestMapping("/admin/techerassignment")
public class TecherAssignmentController {
    @Autowired
    TeacherAssignmentService teacherassignmentservice;
    @PostMapping
    public ResponseEntity<String> assignTeacher(@RequestBody TeacherAssignmentRequestDto dto){
        teacherassignmentservice.AssignTeacher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Teacher Assigned to Section"+dto.getSectionId());
    }
    
}
