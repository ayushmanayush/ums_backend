package com.ums.backend.dto;

import lombok.Data;

@Data
public class SubjectRequestDto {
    private String subjectId;
    private String subjectName;
    private int credit;
}
