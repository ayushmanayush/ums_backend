package com.ums.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ums.backend.dto.*;
import com.ums.backend.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(
            @RequestBody AttendanceCreateRequestDto dto) {

        attendanceService.markAttendance(dto);
        return ResponseEntity.ok("Attendance marked successfully");
    }

    @GetMapping("/teacher/{attendanceId}")
    public ResponseEntity<AttendanceTeacherViewResponseDto>
    viewAttendance(@PathVariable Long attendanceId) {

        return ResponseEntity.ok(
                attendanceService.viewAttendance(attendanceId)
        );
    }

    @GetMapping("/student/{regId}")
    public ResponseEntity<List<AttendanceRecordTeacherViewDto>>
    studentAttendance(@PathVariable String regId) {

        return ResponseEntity.ok(
                attendanceService.studentAttendance(regId)
        );
    }
    @GetMapping
    public ResponseEntity<AttendanceTeacherViewResponseDto> viewAttendance(
            @RequestParam String sectionId,
            @RequestParam String subjectId,
            @RequestParam LocalDate date
    ) {
        return ResponseEntity.ok(
                attendanceService.viewAttendance(
                        sectionId, subjectId, date
                )
        );
    }
}