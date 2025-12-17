package com.ums.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ums.backend.dto.StudentResponseDto;
import com.ums.backend.dto.SubjectResponseDto;
import com.ums.backend.dto.TeacherResponseDto;
import com.ums.backend.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/{regId}")
    public ResponseEntity<StudentResponseDto> getProfile(@PathVariable String regId) {
        return ResponseEntity.ok(studentService.findStudent(regId));
    }
    @GetMapping("/{regId}/subjects")
    public ResponseEntity<List<SubjectResponseDto>> getSubjects(@PathVariable String regId) {
        return ResponseEntity.ok(studentService.getSubjectsForStudent(regId));
    }
    @GetMapping("/{regId}/teachers")
    public ResponseEntity<List<TeacherResponseDto>> getTeachers(@PathVariable String regId) {
        return ResponseEntity.ok(studentService.getTeachersForStudent(regId));
    }
}