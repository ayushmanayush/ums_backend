 package com.ums.backend.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;
@Embeddable
@Data
public class TeacherAssignmentId implements Serializable{
private String teacherId;
private String subjectId;
private String sectionId;
 public TeacherAssignmentId(){
 }
 public TeacherAssignmentId(String teacherId,String subjectId,String sectionId){
    this.teacherId = teacherId;
    this.subjectId = subjectId;
    this.sectionId = sectionId;
 }
    
}