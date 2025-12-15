package com.ums.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.dto.DepartmentSubjectRequestDto;
import com.ums.backend.entity.Department;
import com.ums.backend.entity.DepartmentSubject;
import com.ums.backend.entity.Subject;
import com.ums.backend.entity.SubjectDepartmentId;
import com.ums.backend.exception.DepartmentNotFound;
import com.ums.backend.exception.MappingAlreadyExists;
import com.ums.backend.exception.SubjectNotFound;
import com.ums.backend.repository.DepartmentSubjectRepository;
import com.ums.backend.repository.Departmentrepository;
import com.ums.backend.repository.Subjectrepository;

@Service
@Transactional
public class DepartmentSubjectService {
    @Autowired
    DepartmentSubjectRepository departmentsubjectrepo;
    @Autowired
    Departmentrepository departmentrepo;
    @Autowired
    Subjectrepository subjectrepo;
    public void createSubjectDepartmentMap(DepartmentSubjectRequestDto departmentsubject){
        Department dept = departmentrepo.findById(departmentsubject.getDepartment()).orElseThrow(()-> new DepartmentNotFound("Department Not Found with deptId: "+departmentsubject.getDepartment()));
        Subject sub = subjectrepo.findById(departmentsubject.getSubject()).orElseThrow(()-> new SubjectNotFound("Subject Not found with subjectId: "+departmentsubject.getSubject()));
        SubjectDepartmentId newId = new SubjectDepartmentId(dept.getDepartmentId(),sub.getSubjectId());
        if(departmentsubjectrepo.existsById(newId)){
            throw new MappingAlreadyExists("Subject Already Exists in the given department");
        }
        DepartmentSubject newdeptsub = new DepartmentSubject();
        newdeptsub.setDepartment(dept);
        newdeptsub.setSubject(sub);
        newdeptsub.setId(newId);
        departmentsubjectrepo.save(newdeptsub);
    }
}
