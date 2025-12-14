package com.ums.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "subjects")
@Data
public class Subject{
    @Id
    private String subjectId;
    private String subjectName;
    private int credit;
}
