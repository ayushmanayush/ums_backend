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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/techerassignment")
public class TecherAssignmentController {
    @Autowired
    TeacherAssignmentService teacherassignmentservice;
    @PostMapping
    public ResponseEntity<String> assignTeacher(@RequestBody @Valid TeacherAssignmentRequestDto dto){
        teacherassignmentservice.AssignTeacher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Teacher Assigned to Section"+dto.getSectionId());
    }
    @DeleteMapping("/{teacherId}/{subjectId}/{sectionId}")
    public ResponseEntity<String> deleteTeacherAssign(
       @NotBlank(message = "Teacher Id should not be Blank") @PathVariable String teacherId,
        @NotBlank(message = "Subject Id should not be Blank") @PathVariable String subjectId,
        @NotBlank(message = "Section Id should not be Blank")@PathVariable String sectionId) {

    teacherassignmentservice.deleteTeacherAssign(teacherId, subjectId, sectionId);
    return ResponseEntity.noContent().build();
}
    @GetMapping("section/{sectionId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findbySection(@PathVariable @NotBlank(message = "Section id should not be blank") String sectionId){
       List<TeacherAssignmentResponseDto> details =  teacherassignmentservice.getbysection(sectionId);
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("subject/{subjectId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findbysubject(@PathVariable @NotBlank(message = "Subjrct Id should not be blank") String subjectId){
       List<TeacherAssignmentResponseDto> details = teacherassignmentservice.getbysubject(subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("teacher/{tecaherId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findbyteacher(@PathVariable @NotBlank(message = "Teacher Id should not be blank") String tecaherId){
       List<TeacherAssignmentResponseDto> details =  teacherassignmentservice.getbyteacher(tecaherId);
       return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("/findsectiontaught/{teacherId}/{subjectId}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findsectionbyteachersubjectId(@PathVariable @NotBlank(message = "Teacher Id should not be blank") String teacherId,@PathVariable @NotBlank(message = "Subject id should not be blank") String subjectId){
        List<TeacherAssignmentResponseDto> details = teacherassignmentservice.getbyteacherSubject(teacherId, subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(details);
    }
    @GetMapping("/findteacherbysectionsubject/{subjectId}/{sectionId}")
    public ResponseEntity<TeacherAssignmentResponseDto> findteacherbysectionsubjectId(@PathVariable @NotBlank(message = "Subject Id should not be blank") String subjectId,@PathVariable @NotBlank(message = "Section id should not be blank") String sectionId){
       TeacherAssignmentResponseDto details =  teacherassignmentservice.getbysectionSubject(subjectId,sectionId);
       return ResponseEntity.status(HttpStatus.OK).body(details);

    }
    @GetMapping("/findsubjectbysectioncredit/{sectionId}/{credit}")
    public ResponseEntity<List<TeacherAssignmentResponseDto>> findsubjectbysectioncredit(@PathVariable @NotBlank(message = "Section Id should not be blank") String sectionId,@PathVariable @NotNull(message = "Credit Should not be blank") int credit){
        List<TeacherAssignmentResponseDto> list =teacherassignmentservice.getbysectioncredit(sectionId,credit);
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }
}
