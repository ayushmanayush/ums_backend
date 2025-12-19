package com.ums.backend.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AttendanceCreateRequestDto {
    @NotNull(message = "Date should not be blank")
    private LocalDate date;
    @NotBlank(message = "section id should not be blank")
    private String sectionId;
    @NotBlank(message = "Subject id should not be blank")
    private String subjectId;
    @NotBlank(message = "Teacher id should not be blank")
    private String teacherId;
    @NotEmpty(message = "List Is Empty!")
    private List<StudentAttendanceDto> students;
}