package com.ums.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "teacher")
@Data
public class Teacher {
    @Id
    private String teacherId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;
    private LocalDate dateJoined;
    private String address;

}
