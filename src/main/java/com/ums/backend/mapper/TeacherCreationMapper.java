package com.ums.backend.mapper;
import com.ums.backend.entity.*;

import org.springframework.stereotype.Component;
import java.util.*;
import com.ums.backend.dto.*;
@Component
public class TeacherCreationMapper {
    public Teacher toEntityDto(TeacherRequestDto teacher){
        Teacher newTeacher = new Teacher();
        newTeacher.setFirstName(teacher.getFirstName());
        newTeacher.setLastName(teacher.getLastName());
        newTeacher.setPhoneNumber(teacher.getPhoneNumber());
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setAddress(teacher.getAddress());
        return newTeacher;
    }
    public TeacherResponseDto toResponseDto(Teacher teacher){
        TeacherResponseDto teacher_sender = new TeacherResponseDto();
        teacher_sender.setTeacherId(teacher.getTeacherId());
        teacher_sender.setFirstName(teacher.getFirstName());
        teacher_sender.setLastName(teacher.getLastName());
        teacher_sender.setDepartment(teacher.getDepartment().getDepartmentId());
        teacher_sender.setDateJoined(teacher.getDateJoined());
        teacher_sender.setPhoneNumber(teacher.getPhoneNumber());
        teacher_sender.setEmail(teacher.getEmail());
        return teacher_sender;
    }
    public List<TeacherResponseDto> toList(List<Teacher> teacher){
        List<TeacherResponseDto> list = new ArrayList<>();
        for(Teacher t : teacher){
        list.add(toResponseDto(t));
        }
        return list;

    }
}
