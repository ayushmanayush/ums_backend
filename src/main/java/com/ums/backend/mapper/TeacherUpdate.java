package com.ums.backend.mapper;
import org.springframework.stereotype.Component;

import com.ums.backend.dto.*;
import com.ums.backend.entity.*;
@Component
public class TeacherUpdate {
    public Teacher updateTeacher(TeacherRequestDto teacher, Teacher existing){
        existing.setFirstName(teacher.getFirstName());
        existing.setLastName(teacher.getLastName());
        existing.setAddress(teacher.getAddress());
        existing.setDepartment(teacher.getDepartment());
        existing.setEmail(teacher.getEmail());
        existing.setPhoneNumber(teacher.getPhoneNumber());
        return existing;
    }
    
}
