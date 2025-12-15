package com.ums.backend.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;
@Embeddable
@Data
public class SubjectDepartmentId implements Serializable{
    private String departmentId;
    private String subjectId;
    public SubjectDepartmentId(){
        
    }
    public SubjectDepartmentId(String departmentId, String subjectId) {
        this.departmentId = departmentId;
        this.subjectId = subjectId;
    }
}
