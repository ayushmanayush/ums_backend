package com.ums.backend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AttendanceCreateRequestDto {
    private LocalDate date;
    private String sectionId;
    private String subjectId;
    private String teacherId;
    private List<StudentAttendanceDto> students;
}