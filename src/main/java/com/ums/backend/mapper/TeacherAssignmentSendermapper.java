package com.ums.backend.mapper;

import org.springframework.stereotype.Component;

import com.ums.backend.dto.TeacherAssignmentResponseDto;
import com.ums.backend.entity.TeachingAssignment;
@Component
public class TeacherAssignmentSendermapper {
    public TeacherAssignmentResponseDto intosender(TeachingAssignment teacherAssignment){
        TeacherAssignmentResponseDto tard = new TeacherAssignmentResponseDto();
        tard.setSectionId(teacherAssignment.getSection().getSectionId());
        tard.setSubjectId(teacherAssignment.getSubject().getSubjectId());
        tard.setTeacherId(teacherAssignment.getTeacher().getTeacherId());
        return tard;
    }
}
