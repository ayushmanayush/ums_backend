package com.ums.backend.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class DepartmentRequestDto {
    @NotBlank(message = "Department id should not be blank")
    private String departmentId;
    @NotBlank(message = "Department name should not be blank")
    private String departmentName;
}
