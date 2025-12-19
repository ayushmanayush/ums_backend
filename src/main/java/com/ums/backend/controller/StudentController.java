package com.ums.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ums.backend.dto.StudentResponseDto;
import com.ums.backend.dto.SubjectResponseDto;
import com.ums.backend.dto.TeacherResponseDto;
import com.ums.backend.service.StudentService;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/{regId}")
    public ResponseEntity<StudentResponseDto> getProfile(@PathVariable @NotBlank(message = "Registration Id should not be blank") String regId) {
        return ResponseEntity.ok(studentService.findStudent(regId));
    }
    @GetMapping("/{regId}/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getSubjects(@PathVariable @NotBlank(message = "Registration Id should not be blank") String regId) {
        return ResponseEntity.ok(studentService.getSubjectsForStudent(regId));
    }
    @GetMapping("/{regId}/teachers")
    public ResponseEntity<List<TeacherResponseDto>> getTeachers(@PathVariable @NotBlank(message = "Registration id should not Be Blank") String regId) {
        return ResponseEntity.ok(studentService.getTeachersForStudent(regId));
    }
}