package com.ums.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.ums.backend.dto.*;
import com.ums.backend.service.AttendanceService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/attendance")
@Validated
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(
            @RequestBody @Valid AttendanceCreateRequestDto dto) {

        attendanceService.markAttendance(dto);
        return ResponseEntity.ok("Attendance marked successfully");
    }

    @GetMapping("/teacher/{attendanceId}")
    public ResponseEntity<AttendanceTeacherViewResponseDto>
    viewAttendance(@PathVariable @NotBlank(message = "Attendance Id should not be null") Long attendanceId) {

        return ResponseEntity.ok(
                attendanceService.viewAttendance(attendanceId)
        );
    }

    @GetMapping("/student/{regId}")
    public ResponseEntity<List<AttendanceRecordTeacherViewDto>>
    studentAttendance(@PathVariable @NotBlank(message = "Registration Id should not be Blank") String regId) {

        return ResponseEntity.ok(attendanceService.studentAttendance(regId)
        );
    }
    @GetMapping
    public ResponseEntity<AttendanceTeacherViewResponseDto> viewAttendance(@RequestParam @NotBlank(message = "Registration id should not be Blank") String sectionId,
            @RequestParam @NotBlank(message = "Subject Id should not be Blank") String subjectId,@RequestParam @NotNull(message = "Date Should not be Blank") LocalDate date
    ) {
        return ResponseEntity.ok(
                attendanceService.viewAttendance(sectionId, subjectId, date)
        );
    }
}