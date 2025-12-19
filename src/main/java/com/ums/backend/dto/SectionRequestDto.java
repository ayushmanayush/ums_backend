package com.ums.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SectionRequestDto {
    @NotBlank(message = "Section id should not be blank")
    private String sectionId;
    @NotBlank(message = "Department Id should not be blank")
    private String departmentName;
}
