package com.ums.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentSubjectRequestDto {
    @NotBlank(message = "Department Id should not be blank")
    private String department;
    @NotBlank(message = "Subject Id should not be blank")
    private String subject;
}
