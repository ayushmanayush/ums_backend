package com.ums.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ums.backend.entity.AttendanceRecord;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {

    Optional<AttendanceRecord> findByAttendance_AttendanceIdAndStudent_RegId(Long attendanceId,String regId);

    List<AttendanceRecord> findByStudent_RegId(String regId);

    List<AttendanceRecord> findByAttendance_Section_SectionId(String sectionId);

    List<AttendanceRecord> findByAttendance_AttendanceId(Long attendanceId);
    boolean existsByAttendance_AttendanceIdAndStudent_Regid(Long attendanceId,String regId);
}