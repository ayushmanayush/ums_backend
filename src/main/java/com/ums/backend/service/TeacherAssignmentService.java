package com.ums.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.dto.TeacherAssignmentRequestDto;
import com.ums.backend.dto.TeacherAssignmentResponseDto;
import com.ums.backend.entity.Section;
import com.ums.backend.entity.Subject;
import com.ums.backend.entity.Teacher;
import com.ums.backend.entity.TeacherAssignmentId;
import com.ums.backend.entity.TeachingAssignment;
import com.ums.backend.exception.SectionNotFound;
import com.ums.backend.exception.SubjectNotFound;
import com.ums.backend.exception.TeacherNotFoundException;
import com.ums.backend.mapper.TeacherAssignmentSendermapper;
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
    @Autowired
    TeacherAssignmentSendermapper teachersendermapper;

    public void AssignTeacher(TeacherAssignmentRequestDto dto){
        Section section = sectionrepo.findById(dto.getSectionId()).orElseThrow(()-> new SectionNotFound("section Not available with section id: "+dto.getSectionId()));
        Subject subject = subjectrepo.findById(dto.getSubjectId()).orElseThrow(()-> new SubjectNotFound("Subject Not Found with subject id: "+dto.getSubjectId()));
        Teacher teacher = teacherrepo.findById(dto.getTeacherId()).orElseThrow(()-> new TeacherNotFoundException("Tecaher Not Available with teacher id: "+dto.getTeacherId()));
        if (teacherAssignmentrepo.existsBySubject_subjectIdAndSection_sectionId(
            dto.getSubjectId(), dto.getSectionId())) {
        throw new RuntimeException(
            "Teacher already assigned for this subject and section");
    }
        
        TeacherAssignmentId newId = new TeacherAssignmentId(dto.getTeacherId(),dto.getSubjectId(),dto.getSectionId());
        TeachingAssignment newAssignment = new TeachingAssignment();
        newAssignment.setId(newId);
        newAssignment.setSection(section);
        newAssignment.setSubject(subject);
        newAssignment.setTeacher(teacher);
        teacherAssignmentrepo.save(newAssignment);
    }
    public void deleteTeacherAssign(String teacherId, String subjectId, String sectionId){
        sectionrepo.findById(sectionId).orElseThrow(()-> new SectionNotFound("Section Not found with dection Id: "+sectionId));
        subjectrepo.findById(subjectId).orElseThrow(()-> new SubjectNotFound("Subject Not found with subject Id: "+subjectId));
        teacherrepo.findById(teacherId).orElseThrow(()-> new TeacherNotFoundException("Tecaher Not found with teacher Id: "+teacherId));
        
        TeacherAssignmentId id = new TeacherAssignmentId(teacherId, subjectId, sectionId);
        TeachingAssignment assignment = teacherAssignmentrepo.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Teacher assignment not found"));
        teacherAssignmentrepo.delete(assignment);
    }
    public List<TeacherAssignmentResponseDto> getbyteacher(String teacherId){
        teacherrepo.findById(teacherId).orElseThrow(()-> new TeacherNotFoundException("Teacher Not found with teacher Id: "+teacherId));
        List<TeachingAssignment> subjectsSection = teacherAssignmentrepo.findByTeacher_teacherId(teacherId);
        List<TeacherAssignmentResponseDto> list = new ArrayList<>();
        for(TeachingAssignment i : subjectsSection){
            list.add(teachersendermapper.intosender(i));
        }
        return list;

    }
    public List<TeacherAssignmentResponseDto> getbysubject(String subjectId){
        subjectrepo.findById(subjectId).orElseThrow(()-> new SubjectNotFound("Subject Not found with subject Id: "+subjectId));
        List<TeachingAssignment> teacherSection = teacherAssignmentrepo.findBySubject_subjectId(subjectId);
        List<TeacherAssignmentResponseDto> list = new ArrayList<>();
        for(TeachingAssignment i : teacherSection){
            list.add(teachersendermapper.intosender(i));
        }
        return list;
    }
    public List<TeacherAssignmentResponseDto> getbysection(String sectionId){
         sectionrepo.findById(sectionId).orElseThrow(()-> new SectionNotFound("Section Not found with dection Id: "+sectionId));
        List<TeachingAssignment> subjectTeacher = teacherAssignmentrepo.findBySection_sectionId(sectionId);
        List<TeacherAssignmentResponseDto> list = new ArrayList<>();
        for(TeachingAssignment i : subjectTeacher){
            list.add(teachersendermapper.intosender(i));
        }
        return list;
    }
    public List<TeacherAssignmentResponseDto> getbyteacherSubject(String teacherId,String subjectId){
        teacherrepo.findById(teacherId).orElseThrow(()-> new TeacherNotFoundException("Teacher not found with teacher Id: "+teacherId));
        subjectrepo.findById(subjectId).orElseThrow(()-> new SubjectNotFound("Subject Not found with subject Id: "+subjectId));
        List<TeachingAssignment> section = teacherAssignmentrepo.findByTeacher_teacherIdAndSubject_subjectId(teacherId, subjectId);
        List<TeacherAssignmentResponseDto> list = new ArrayList<>();
        for(TeachingAssignment i : section){
            list.add(teachersendermapper.intosender(i));
        }
        return list;
    }
    public List<TeacherAssignmentResponseDto> getbysectioncredit(String sectionId, int credit){
        sectionrepo.findById(sectionId).orElseThrow(()-> new SectionNotFound("Section Not found with dection Id: "+sectionId));
        List<TeachingAssignment> subjects= teacherAssignmentrepo.findBySection_sectionIdAndSubject_credit(sectionId, credit);
        List<TeacherAssignmentResponseDto> list = new ArrayList<>();
        for(TeachingAssignment i : subjects){
            list.add(teachersendermapper.intosender(i));
        }
        return list;
    }
    public TeacherAssignmentResponseDto getbysectionSubject(String subjectId,String sectionId){
        subjectrepo.findById(subjectId).orElseThrow(()-> new SubjectNotFound("Subject Not Found with Subject Id: "+subjectId));
        sectionrepo.findById(sectionId).orElseThrow(()-> new SectionNotFound("Section Not found wioth Section id: "+sectionId));
        TeachingAssignment teacher = teacherAssignmentrepo.findBySubject_subjectIdAndSection_sectionId(subjectId, sectionId);
        TeacherAssignmentResponseDto tosend = teachersendermapper.intosender(teacher);
        return tosend;
    }

}
