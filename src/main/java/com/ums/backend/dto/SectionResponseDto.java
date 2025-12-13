package com.ums.backend.dto;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class SectionResponseDto{
    @NotBlank
    private String sectionId;
    private LocalDate createdAt;
    private String departmentName;
}