package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.ums.backend.service.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import com.ums.backend.dto.*;
import lombok.Data;
import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.TeacherRequestDto;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@Data
@RequestMapping("teacher")
@Validated
public class TeacherController {

    @Autowired
    TeacherService teacherservice;

    @PostMapping
    public ResponseEntity<TeacherResponseDto> createTeacher(@RequestBody @Valid TeacherRequestDto teacher){
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherservice.crateTeacherDto(teacher));
    }
    @GetMapping
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers(){
        return ResponseEntity.ok(teacherservice.getALLTeachers());
    }
    @GetMapping("/{regId}")
    public ResponseEntity<TeacherResponseDto> findTeacher(@PathVariable @NotBlank(message = "Teacher Registration Id should not be Blank") String regId){
        return ResponseEntity.ok(teacherservice.findbyId(regId));
    }
    @PutMapping("{regid}")
    public ResponseEntity<TeacherResponseDto> toUpdateTeacher(@PathVariable @NotBlank(message = "Teacher Registration Id should not be Blank") String regid, @RequestBody @Valid TeacherRequestDto teacher) {
        return ResponseEntity.ok(teacherservice.updateTecaherbyreg(regid, teacher));
    }
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<String> deleteTeacher(@PathVariable @NotBlank(message = "Teacher Registration Id should not be blank") String teacherId){
        teacherservice.deleteTeacher(teacherId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    
}
