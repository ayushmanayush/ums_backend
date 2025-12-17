package com.ums.backend.dto;


import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class AttendanceTeacherViewResponseDto {
    private Long attendanceId;
    private LocalDate date;
    private String sectionId;
    private String subjectId;
    private List<AttendanceRecordTeacherViewDto> students;
}