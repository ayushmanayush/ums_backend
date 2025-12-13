package com.ums.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "sections")
public class Section {
@Id
private String sectionId;
private LocalDate createdAt;

@ManyToOne
@JoinColumn(name = "departmentId",nullable = false)
private Department department;
}
