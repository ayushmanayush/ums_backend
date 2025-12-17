package com.ums.backend.dto;
import lombok.Data;

@Data
public class AttendanceRecordTeacherViewDto {
    private String regId;
    private String studentName;
    private boolean present;
}