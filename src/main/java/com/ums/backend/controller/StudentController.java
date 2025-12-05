package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.StudentRequestDto;
import com.ums.backend.entity.Student;
import com.ums.backend.service.StudentService;
import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.ums.backend.dto.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentservice;

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentRequestDto student ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservice.createStudentDto(student));
    }
    @GetMapping
    public ResponseEntity<List<Student>> getallStudent(){
        return ResponseEntity.ok(studentservice.getallstudents());
    }
    @GetMapping("/{regId}")
    public ResponseEntity<Student> getalldetails(@PathVariable String regId){
        return ResponseEntity.ok(studentservice.findStudent(regId));
    }
    @PutMapping("/{regId}")
    public ResponseEntity<String> UpdateStudent(@PathVariable String regId, @RequestBody Student stuobj) {
        studentservice.updateStudent(regId, stuobj);

        return ResponseEntity.ok(regId+ " Updated Successfully");
    }
    @PatchMapping("/{regId}")
    public ResponseEntity<String> PatchUpdate(@PathVariable String regId, @RequestBody Student updatingValue){
        String updateprocess = studentservice.UpdatePatch(regId, updatingValue);
        return ResponseEntity.ok(updateprocess);
    }
    @DeleteMapping("/{regId}")
    public ResponseEntity<Void> deleteStudenT(@PathVariable String regId){
        studentservice.deleteStudent(regId);
        return ResponseEntity.noContent().build();
    }

}   
