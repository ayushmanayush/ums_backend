package com.ums.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.DepartmentRequestDto;
import com.ums.backend.dto.DepartmentResponseDto;
import com.ums.backend.service.DepartmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/department")
@Validated
public class DepartmentController {
    @Autowired
    DepartmentService departmentservice;
    @PostMapping
    public ResponseEntity<DepartmentResponseDto> departmentCreation(@RequestBody @Valid DepartmentRequestDto department){
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentservice.createDepartment(department));
    }
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> getDepartment(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentservice.getDto());
    }
    @PutMapping("/{depId}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable @NotBlank(message = "Department Id should not be blank") String depId,@RequestBody @Valid DepartmentRequestDto department){
        return ResponseEntity.status(HttpStatus.OK).body(departmentservice.updateDepartment(depId, department));
    }
    @DeleteMapping("/{depId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable @NotBlank(message = "Department Id should not be blank") String depId){
        departmentservice.deleteDepartment(depId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
