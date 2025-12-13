package com.ums.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    private String Regid;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String phoneNumber;
    private String address;
    @ManyToOne
    @JoinColumn(name = "departmentId",nullable = false)
    private Department departmentName;
    @ManyToOne
    @JoinColumn(name ="sectionId",nullable = true)
    private Section sectionName;
    private String yearAdmission;
}
