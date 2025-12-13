package com.ums.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SectionRequestDto {
    @NotBlank
    private String sectionId;
    @NotBlank
    private String departmentName;
}
