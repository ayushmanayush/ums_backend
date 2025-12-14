package com.ums.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.SubjectRequestDto;
import com.ums.backend.dto.SubjectResponseDto;
import com.ums.backend.dto.SubjectUpdateRequestDto;
import com.ums.backend.service.SubjectService;

@RestController
@RequestMapping("admin/subject")

public class SubjectController {
    @Autowired
    SubjectService subjectservice;
    @PostMapping
    public ResponseEntity<SubjectResponseDto> createSubject(@RequestBody SubjectRequestDto subject){
       SubjectResponseDto value =  subjectservice.createSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(value);
    }
    @PutMapping("/{subjectId}")
    public ResponseEntity<SubjectResponseDto> updateSubject(@PathVariable String subjectId,@RequestBody SubjectUpdateRequestDto subject){
        SubjectResponseDto value= subjectservice.updateSubject(subjectId, subject);
        return ResponseEntity.status(HttpStatus.OK).body(value);
    }
    @GetMapping
    public ResponseEntity<List<SubjectResponseDto>> getAllSubject(){
        return ResponseEntity.status(HttpStatus.OK).body(subjectservice.findAllSubject());
    }
    @GetMapping("/{subjectId}")
    public ResponseEntity<SubjectResponseDto> getSubjectById(@PathVariable String subjectId){
        return ResponseEntity.status(HttpStatus.OK).body(subjectservice.getSubjectById(subjectId));
    }
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable String subjectId){
        subjectservice.deleteSubject(subjectId);
        return ResponseEntity.status(HttpStatus.OK).body("Subject Object Deleted Successfully.");
    }
}
