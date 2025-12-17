package com.ums.backend.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class DepartmentRequestDto {
    @NotBlank
    private String departmentId;
    @NotBlank
    private String departmentName;
}
