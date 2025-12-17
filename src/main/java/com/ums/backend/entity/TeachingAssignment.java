package com.ums.backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "teachingassignment",uniqueConstraints = @UniqueConstraint(columnNames = {"subject_id","section_id"}))
@Data
public class TeachingAssignment {
    @EmbeddedId
    private TeacherAssignmentId id;
    @ManyToOne
    @JoinColumn(name = "teacher_id",nullable = false)
    @MapsId("teacherId")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "subject_id",nullable = false)
    @MapsId("subjectId")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "section_id",nullable = false)
    @MapsId("sectionId")
    private Section section;
}
