package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.DepartmentSubjectRequestDto;
import com.ums.backend.service.DepartmentSubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/departmentsubjectmapping")
public class DepartmentSubjectController {
    @Autowired
    DepartmentSubjectService deptsubjservice;
    @PostMapping
    public ResponseEntity<String> createdeptsubj(@RequestBody @Valid DepartmentSubjectRequestDto deptsubj){
        deptsubjservice.createSubjectDepartmentMap(deptsubj);
        return ResponseEntity.status(HttpStatus.CREATED).body("Department <-> Subject Mapped Successfully");
    }
}
