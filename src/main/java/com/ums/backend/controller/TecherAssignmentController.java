package com.ums.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.TeacherAssignmentRequestDto;
import com.ums.backend.dto.TeacherAssignmentResponseDto;
import com.ums.backend.service.TeacherAssignmentService;

@RestController
@RequestMapping("/admin/techerassignment")
public class TecherAssignmentController {
    @Autowired
    TeacherAssignmentService teacherassignmentservice;
    @PostMapping
    public ResponseEntity<String> assignTeacher(@RequestBody TeacherAssignmentRequestDto dto){
        teacherassignmentservice.AssignTeacher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Teacher Assigned to Section"+dto.getSectionId());
    }
    @DeleteMapping("/{teacherId}/{subjectId}/{sectionId}")
    public ResponseEntity<String> deleteTeacherAssign(
        @PathVariable String teacherId,
        @PathVariable String subjectId,
        @PathVariable String sectionId) {

    teacherassignmentservice.deleteTeacherAssign(teacherId, subjectId, sectionId);
    return ResponseEntity.noContent().build();
}
    @GetMapping("section/{sectionId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findbySection(@PathVariable String sectionId){
       List<TeacherAssignmentResponseDto> details =  teacherassignmentservice.getbysection(sectionId);
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("subject/{subjectId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findbysubject(@PathVariable String subjectId){
       List<TeacherAssignmentResponseDto> details = teacherassignmentservice.getbysubject(subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("teacher/{tecaherId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findbyteacher(@PathVariable String tecaherId){
       List<TeacherAssignmentResponseDto> details =  teacherassignmentservice.getbyteacher(tecaherId);
       return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("/findsectiontaught/{teacherId}/{subjectId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findsectionbyteachersubjectId(@PathVariable String teacherId,@PathVariable String subjectId){
        List<TeacherAssignmentResponseDto> details = teacherassignmentservice.getbyteacherSubject(teacherId, subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("/findteacherbysectionsubject/{subjectId}/{sectionId}")
    public ResponseEntity<TeacherAssignmentResponseDto> findteacherbysectionsubjectId(@PathVariable String subjectId,@PathVariable String sectionId){
       TeacherAssignmentResponseDto details =  teacherassignmentservice.getbysectionSubject(subjectId,sectionId);
       return ResponseEntity.status(HttpStatus.OK).body(details);

    }
    @GetMapping("/findsubjectbysectioncredit/{sectionId}/{credit}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findsubjectbysectioncredit(@PathVariable String sectionId,@PathVariable int credit){
        List<TeacherAssignmentResponseDto> list =teacherassignmentservice.getbysectioncredit(sectionId,credit);
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }
}
