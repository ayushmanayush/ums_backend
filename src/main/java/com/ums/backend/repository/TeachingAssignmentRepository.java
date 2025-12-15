package com.ums.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ums.backend.entity.TeacherAssignmentId;
import com.ums.backend.entity.TeachingAssignment;

public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment,TeacherAssignmentId>{
    
}
