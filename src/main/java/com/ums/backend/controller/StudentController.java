package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.ums.backend.entity.Student;
import com.ums.backend.service.StudentService;
import java.util.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentservice;

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
    @PutMapping("/{regId}")
    public String UpdateStudent(@PathVariable String regId, @RequestBody Student stuobj) {
        studentservice.updateStudent(regId, stuobj);

        return "Successfully updated Student with RegId: "+regId;
    }
    @PatchMapping("/{regId}")
    public String PatchUpdate(@PathVariable String regId, @RequestBody Student updatingValue){
        String updateprocess = studentservice.UpdatePatch(regId, updatingValue);
        return updateprocess;
    }
    @DeleteMapping("/{regId}")
    public String deleteStudenT(@PathVariable String regId){
        String deleted = studentservice.deleteStudent(regId);
        return deleted;
    }

}   
