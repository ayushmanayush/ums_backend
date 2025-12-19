package com.ums.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubjectRequestDto {
    @NotBlank(message = "Subject Id should not be Blank")
    private String subjectId;
    @NotBlank(message = "Subject name should not be Blank")
    private String subjectName;
    @NotNull(message = "credit value should not be blank")
    private int credit;
}
