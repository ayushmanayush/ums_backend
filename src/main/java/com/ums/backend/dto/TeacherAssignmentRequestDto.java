package com.ums.backend.dto;

import lombok.Data;

@Data
public class TeacherAssignmentRequestDto {
    private String teacherId;
    private String sectionId;
    private String subjectId;
}
