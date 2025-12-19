package com.ums.backend.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.dto.*;
import com.ums.backend.entity.*;
import com.ums.backend.repository.*;

@Service
@Transactional
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepo;

    @Autowired
    private SectionRepository sectionRepo;

    @Autowired
    private Subjectrepository subjectRepo;

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private Studentrepository studentRepo;

    public void markAttendance(AttendanceCreateRequestDto dto) {

        Section section = sectionRepo.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found"));

        Subject subject = subjectRepo.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Teacher teacher = teacherRepo.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Attendance attendance = attendanceRepo
                .findBySection_SectionIdAndSubject_SubjectIdAndDate(
                        dto.getSectionId(),
                        dto.getSubjectId(),
                        dto.getDate()
                )
                .orElseGet(() -> {
                    Attendance a = new Attendance();
                    a.setSection(section);
                    a.setSubject(subject);
                    a.setTeacher(teacher);
                    a.setDate(dto.getDate());
                    return attendanceRepo.save(a);
                });

        for (StudentAttendanceDto s : dto.getStudents()) {

            Student student = studentRepo.findById(s.getRegId())
                    .orElseThrow(() ->
                            new RuntimeException("Student not found"));

            boolean exists =
                    attendanceRecordRepo
                            .existsByAttendance_AttendanceIdAndStudent_Regid(
                                    attendance.getAttendanceId(),
                                    student.getRegid()
                            );

            if (exists) continue;

            AttendanceRecord record = new AttendanceRecord();
            record.setAttendance(attendance);
            record.setStudent(student);
            record.setPresent(s.isPresent());

            attendanceRecordRepo.save(record);
        }
    }

    public AttendanceTeacherViewResponseDto
    viewAttendance(Long attendanceId) {

        Attendance attendance = attendanceRepo.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        List<AttendanceRecord> records =
                attendanceRecordRepo.findByAttendance_AttendanceId(attendanceId);

        List<AttendanceRecordTeacherViewDto> students =
                records.stream().map(r -> {
                    AttendanceRecordTeacherViewDto dto =
                            new AttendanceRecordTeacherViewDto();
                    dto.setRegId(r.getStudent().getRegid());
                    dto.setStudentName(
                            r.getStudent().getFirstName() + " " +
                            r.getStudent().getLastName()
                    );
                    dto.setPresent(r.isPresent());
                    return dto;
                }).toList();

        AttendanceTeacherViewResponseDto response =
                new AttendanceTeacherViewResponseDto();

        response.setAttendanceId(attendance.getAttendanceId());
        response.setDate(attendance.getDate());
        response.setSectionId(
                attendance.getSection().getSectionId()
        );
        response.setSubjectId(
                attendance.getSubject().getSubjectId()
        );
        response.setStudents(students);

        return response;
    }

    public List<AttendanceRecordTeacherViewDto>
    studentAttendance(String regId) {

        Student student = studentRepo.findById(regId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<AttendanceRecord> records =
                attendanceRecordRepo.findByStudent_Regid(regId);

        return records.stream().map(r -> {
            AttendanceRecordTeacherViewDto dto =
                    new AttendanceRecordTeacherViewDto();
            dto.setRegId(student.getRegid());
            dto.setStudentName(
                    student.getFirstName() + " " +
                    student.getLastName()
            );
            dto.setPresent(r.isPresent());
            return dto;
        }).toList();
    }
    public AttendanceTeacherViewResponseDto viewAttendance(
        String sectionId,
        String subjectId,
        LocalDate date
) {

    Attendance attendance = attendanceRepo
            .findBySection_SectionIdAndSubject_SubjectIdAndDate(
                    sectionId, subjectId, date
            )
            .orElseThrow(() ->
                    new RuntimeException("Attendance not found"));

    List<AttendanceRecord> records =
            attendanceRecordRepo.findByAttendance_AttendanceId(
                    attendance.getAttendanceId());

    List<AttendanceRecordTeacherViewDto> students = new ArrayList<>();

    for (AttendanceRecord r : records) {
        AttendanceRecordTeacherViewDto dto =
                new AttendanceRecordTeacherViewDto();

        dto.setRegId(r.getStudent().getRegid());
        dto.setStudentName(
                r.getStudent().getFirstName() + " " +
                r.getStudent().getLastName()
        );
        dto.setPresent(r.isPresent());

        students.add(dto);
    }

    AttendanceTeacherViewResponseDto response =
            new AttendanceTeacherViewResponseDto();

    response.setAttendanceId(attendance.getAttendanceId());
    response.setDate(attendance.getDate());
    response.setSectionId(sectionId);
    response.setSubjectId(subjectId);
    response.setStudents(students);

    return response;
}
}
