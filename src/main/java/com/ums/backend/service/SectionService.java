package com.ums.backend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.dto.SectionRequestDto;
import com.ums.backend.dto.SectionResponseDto;
import com.ums.backend.entity.Department;
import com.ums.backend.entity.Section;
import com.ums.backend.exception.DepartmentNotFound;
import com.ums.backend.mapper.SectionCreationMapper;
import com.ums.backend.repository.Departmentrepository;
import com.ums.backend.repository.SectionRepository;

@Service
@Transactional
public class SectionService {
    @Autowired
    SectionCreationMapper sectionmapper;
    @Autowired
    SectionRepository sectionrepo;
    @Autowired
    Departmentrepository departmentrepo;
    public SectionResponseDto createSection(SectionRequestDto section){
        Department dept = departmentrepo.findById(section.getDepartmentName()).orElseThrow(() -> new DepartmentNotFound("Enter Valid department Id"));
        Section newSection = sectionmapper.toSectionEntity(section);
        newSection.setDepartment(dept);
        newSection.setCreatedAt(LocalDate.now());
        Section sectionCreated = sectionrepo.save(newSection);
        return sectionmapper.toUser(sectionCreated);
    }
}
