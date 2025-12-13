package com.ums.backend.mapper;

import org.springframework.stereotype.Component;

import com.ums.backend.dto.SectionRequestDto;
import com.ums.backend.dto.SectionResponseDto;
import com.ums.backend.entity.Section;
@Component
public class SectionCreationMapper {
    public Section toSectionEntity(SectionRequestDto section){
        Section newSection = new Section();
        newSection.setSectionId(section.getSectionId());
        return newSection;
    }
    public SectionResponseDto toUser(Section section){
        SectionResponseDto sectionToSend = new SectionResponseDto();
        sectionToSend.setSectionId(section.getSectionId());
        sectionToSend.setDepartmentName(section.getDepartment().getDepartmentId());
        sectionToSend.setCreatedAt(section.getCreatedAt());
        return sectionToSend;
    }
}
