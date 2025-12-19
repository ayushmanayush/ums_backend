package com.ums.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubjectUpdateRequestDto {
    @NotBlank(message = "Subject Name should not be Blank")
    private String subjectName;
    @NotNull(message = "Credit should not be Blank")
    private int credit;
}
