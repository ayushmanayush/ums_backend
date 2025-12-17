package com.ums.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "attendance")
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attendanceId;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "sectionid",nullable = false)
    private Section section;
    @ManyToOne
    @JoinColumn(name = "subjectId",nullable = false)
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "teacherId",nullable = false)
    private Teacher teacher;

}
