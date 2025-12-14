package com.ums.backend.mapper;
import org.springframework.stereotype.Component;

import com.ums.backend.dto.SubjectResponseDto;
import com.ums.backend.dto.SubjectUpdateRequestDto;
import com.ums.backend.entity.Subject;
@Component
public class SubjectUpdatemapper {
    public Subject updateToEntity(SubjectUpdateRequestDto subject , Subject subjectToupdate){
        subjectToupdate.setSubjectName(subject.getSubjectName());
        subjectToupdate.setCredit(subject.getCredit());
        return subjectToupdate;
    }
    public SubjectResponseDto returnUpdatedEntitydto(Subject subject){
        SubjectResponseDto updatedSubject = new SubjectResponseDto();
        updatedSubject.setSubjectId(subject.getSubjectId());
        updatedSubject.setSubjectName(subject.getSubjectName());
        updatedSubject.setCredit(subject.getCredit());
        return updatedSubject;
    }
}
