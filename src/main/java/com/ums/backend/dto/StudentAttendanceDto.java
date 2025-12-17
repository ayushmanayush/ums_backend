package com.ums.backend.dto;

import lombok.Data;

@Data
public class StudentAttendanceDto {
    private String regId;
    private boolean present;
}