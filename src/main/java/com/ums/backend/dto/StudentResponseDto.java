package com.ums.backend.dto;

import lombok.Data;

@Data
public class StudentResponseDto {
    private String Regid;
    private String firstName;
    private String lastName;
    private String department;
    private String yearOfAdmission;
}