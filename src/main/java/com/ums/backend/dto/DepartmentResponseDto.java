package com.ums.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentResponseDto {
    @NotBlank
    private String departmentId;
    @NotBlank
    private String departmentName;
    @NotBlank
    private LocalDate createdAt;
}
