package com.ums.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ums.backend.dto.SubjectRequestDto;
import com.ums.backend.dto.SubjectResponseDto;
import com.ums.backend.dto.SubjectUpdateRequestDto;
import com.ums.backend.entity.Subject;
import com.ums.backend.exception.SubjectNotFound;
import com.ums.backend.mapper.SubjectCreationMapper;
import com.ums.backend.mapper.SubjectUpdatemapper;
import com.ums.backend.repository.Subjectrepository;
@Service
@Transactional
public class SubjectService {
    @Autowired
    SubjectCreationMapper subjectmapper;
    @Autowired
    Subjectrepository subjectrepo;
    @Autowired
    SubjectUpdatemapper subjectupdatemapper;
    public SubjectResponseDto createSubject(SubjectRequestDto subject) {
        Subject newSubject = subjectmapper.toSubjectEntity(subject);
        Subject createdSubject = subjectrepo.save(newSubject);
        return subjectmapper.toUser(createdSubject);
    }
    public List<SubjectResponseDto> findAllSubject(){
        List<Subject> list = subjectrepo.findAll();
        List<SubjectResponseDto> listTosend = new ArrayList<>();
        for(Subject i : list){
            listTosend.add(subjectmapper.toUser(i));
        }
        return listTosend;
        
    }
    public SubjectResponseDto getSubjectById(String subjectId){
        Subject value = subjectrepo.findById(subjectId).orElseThrow(()->  new SubjectNotFound("Subject not found with Subjectid: "+subjectId));
        return subjectmapper.toUser(value);

    }
    public SubjectResponseDto updateSubject(String subjectId,SubjectUpdateRequestDto subject){
        Subject subjectToupdate  = subjectrepo.findById(subjectId).orElseThrow(()-> new SubjectNotFound("Subject not found with Subjectid: "+subjectId));
        Subject newSubjectt = subjectupdatemapper.updateToEntity(subject, subjectToupdate);
        subjectToupdate = subjectrepo.save(newSubjectt);
        return subjectupdatemapper.returnUpdatedEntitydto(subjectToupdate);
    }
    public void deleteSubject(String subjectId){
        subjectrepo.findById(subjectId).orElseThrow(()-> new SubjectNotFound("Subject is not present with Subject Id: "+subjectId));
        subjectrepo.deleteById(subjectId);
    }
}
