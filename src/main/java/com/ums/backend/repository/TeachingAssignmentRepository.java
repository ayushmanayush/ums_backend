package com.ums.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ums.backend.entity.TeacherAssignmentId;
import com.ums.backend.entity.TeachingAssignment;

public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment,TeacherAssignmentId>{
    List<TeachingAssignment> findBySection_sectionId(String sectionId);
    List<TeachingAssignment> findBySubject_subjectId(String subjectId);
    List<TeachingAssignment> findByTeacher_teacherId(String teacherId);
    List<TeachingAssignment> findByTeacher_teacherIdAndSubject_subjectId(String teacherId, String subjectId);
    TeachingAssignment findBySubject_subjectIdAndSection_sectionId(String subjectId,String sectionId);
    List<TeachingAssignment> findBySection_sectionIdAndSubject_credit(String sectionId, int credit);
    boolean existsBySubject_subjectIdAndSection_sectionId(String subjectId, String sectionId);
}
