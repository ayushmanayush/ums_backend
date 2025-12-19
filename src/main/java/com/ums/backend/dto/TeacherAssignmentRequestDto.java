package com.ums.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeacherAssignmentRequestDto {
    @NotBlank(message = "Department should not be Blank")
    private String teacherId;
    @NotBlank(message = "Section id should not be blank")
    private String sectionId;
    @NotBlank(message = "Subject Id should not be Blank")
    private String subjectId;
}
