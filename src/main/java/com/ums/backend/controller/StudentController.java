package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.entity.Student;
import com.ums.backend.repository.Studentrepository;
import com.ums.backend.service.StudentService;
import java.util.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final Studentrepository studentrepository;
    @Autowired
    private StudentService studentservice;

    StudentController(Studentrepository studentrepository) {
        this.studentrepository = studentrepository;
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student ) {
        return studentservice.createStudent(student);
    }
    @GetMapping
    public List<Student> getallStudent(){
        return studentservice.getallstudents();
    }
    @GetMapping("/{regId}")
    public Student getalldetails(@PathVariable String regId){
        return studentservice.findStudent(regId);
    }

}   
