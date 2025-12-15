package com.ums.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.dto.TeacherAssignmentRequestDto;
import com.ums.backend.entity.Section;
import com.ums.backend.entity.Subject;
import com.ums.backend.entity.Teacher;
import com.ums.backend.entity.TeacherAssignmentId;
import com.ums.backend.entity.TeachingAssignment;
import com.ums.backend.exception.SectionNotFound;
import com.ums.backend.exception.SubjectNotFound;
import com.ums.backend.exception.TeacherNotFoundException;
import com.ums.backend.repository.SectionRepository;
import com.ums.backend.repository.Subjectrepository;
import com.ums.backend.repository.TeacherRepository;
import com.ums.backend.repository.TeachingAssignmentRepository;

@Service
@Transactional
public class TeacherAssignmentService {
    @Autowired
    TeachingAssignmentRepository teacherAssignmentrepo;
    @Autowired
    SectionRepository sectionrepo;
    @Autowired
    TeacherRepository teacherrepo;
    @Autowired
    Subjectrepository subjectrepo;

    public void AssignTeacher(TeacherAssignmentRequestDto dto){
        System.out.println(dto);
        Section section = sectionrepo.findById(dto.getSectionId()).orElseThrow(()-> new SectionNotFound("section Not available with section id: "+dto.getSectionId()));
        Subject subject = subjectrepo.findById(dto.getSubjectId()).orElseThrow(()-> new SubjectNotFound("Subject Not Found with subject id: "+dto.getSubjectId()));
        Teacher teacher = teacherrepo.findById(dto.getTeacherId()).orElseThrow(()-> new TeacherNotFoundException("Tecaher Not Available with teacher id: "+dto.getTeacherId()));
        TeacherAssignmentId newId = new TeacherAssignmentId(dto.getTeacherId(),dto.getSubjectId(),dto.getSectionId());
        TeachingAssignment newAssignment = new TeachingAssignment();
        newAssignment.setId(newId);
        newAssignment.setSection(section);
        newAssignment.setSubject(subject);
        newAssignment.setTeacher(teacher);
        teacherAssignmentrepo.save(newAssignment);
    }
}
