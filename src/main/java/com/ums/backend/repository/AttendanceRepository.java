package com.ums.backend.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ums.backend.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

boolean existsByDateAndSection_SectionIdAndSubject_SubjectId(LocalDate date,String sectionId,String subjectId);

    Optional<Attendance> findBySection_SectionIdAndSubject_SubjectIdAndDate(String sectionId,String subjectId,LocalDate date);

    List<Attendance> findBySection_SectionId(String sectionId);

    List<Attendance> findByTeacher_TeacherId(String teacherId);

    List<Attendance> findBySubject_SubjectId(String subjectId);

    List<Attendance> findByDate(LocalDate date);
}