package com.ums.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name ="attendancerecord",uniqueConstraints = @UniqueConstraint(columnNames = {"regid","attendance_id"}))
@Data
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attendanceRecordId;
    @ManyToOne
    @JoinColumn(name = "attendance_id",nullable = false)
    private Attendance attendance;
    @ManyToOne
    @JoinColumn(name = "regid",nullable = false)
    private Student student;

    private boolean present;

}
