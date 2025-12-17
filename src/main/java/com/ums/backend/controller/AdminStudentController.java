package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.StudentRequestDto;
import com.ums.backend.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.ums.backend.dto.*;

@RestController
@RequestMapping("/admin/students")
@Validated
public class AdminStudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody @Valid StudentRequestDto student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudentDto(student));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getallstudents());
    }
    @GetMapping("/{regId}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable @NotBlank(message = "Given Registration Id should not be blank") String regId) {
        return ResponseEntity.ok(studentService.findStudent(regId));
    }
    @PutMapping("/{regId}")
    public ResponseEntity<String> updateStudent(@PathVariable @NotBlank(message = "Given Registration Id should not be blank") String regId,@RequestBody @Valid StudentRequestDto dto) {

        studentService.updateStudent(regId, dto);
        return ResponseEntity.ok("Student updated successfully");
    }
    @PatchMapping("/{regId}")
    public ResponseEntity<String> patchStudent(@PathVariable @NotBlank(message = "Given Registration Id should not be blank") String regId,@RequestBody StudentRequestDto dto) {

        return ResponseEntity.ok(studentService.UpdatePatch(regId, dto));
    }
    @DeleteMapping("/{regId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable @NotBlank(message = "Given Registration Id should not be blank")String regId) {
        studentService.deleteStudent(regId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{regId}/section")
    public ResponseEntity<StudentResponseDto> assignSection(@PathVariable @NotBlank(message = "Given Registration Id should not be blank") String regId,@RequestBody @Valid AssignsectionrequestDto sectionDto) {

        return ResponseEntity.ok(studentService.updateSection(regId, sectionDto));
    }
    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<StudentResponseDto>> getBySection(@PathVariable @NotBlank(message = "Given sectionId should not be blank") String sectionId) {

        return ResponseEntity.ok(studentService.getStudentBySection(sectionId));
    }
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<StudentResponseDto>> getByDepartment(@PathVariable @NotBlank(message = "Given department Id should not be null") String departmentId) {

        return ResponseEntity.ok(studentService.getstudentsByDepartment(departmentId));
    }
}