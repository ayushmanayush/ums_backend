package com.ums.backend.mapper;

import org.springframework.stereotype.Component;

import com.ums.backend.dto.*;
import com.ums.backend.entity.Subject;
@Component
public class SubjectCreationMapper {
    public Subject toSubjectEntity(SubjectRequestDto subject){
        Subject newSubject = new Subject();
        newSubject.setSubjectName(subject.getSubjectName());
        newSubject.setSubjectId(subject.getSubjectId());
        newSubject.setCredit(subject.getCredit());
        return newSubject;
    }
    public SubjectResponseDto toUser(Subject subject){
        SubjectResponseDto subjectToSend = new SubjectResponseDto();
        subjectToSend.setSubjectName(subject.getSubjectName());
        subjectToSend.setSubjectId(subject.getSubjectId());
        subjectToSend.setCredit(subject.getCredit());
        return subjectToSend;
    }
}
