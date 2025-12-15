package com.ums.backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "departmentsubject")
@Data
public class DepartmentSubject {
    @EmbeddedId
    private SubjectDepartmentId id;
    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    @MapsId("departmentId")
    private Department department;
    @ManyToOne
    @JoinColumn(name= "subject_id",nullable = false)
    @MapsId("subjectId")
    private Subject subject;
}
