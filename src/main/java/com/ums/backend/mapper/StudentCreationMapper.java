package com.ums.backend.mapper;
import com.ums.backend.entity.*;

import org.springframework.stereotype.Component;

import com.ums.backend.dto.*;
@Component
public class StudentCreationMapper {
    public Student toEntity(StudentRequestDto student){
        Student newStudent = new Student();
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setPhoneNumber(student.getPhoneNumber());
        newStudent.setEmail(student.getEmail());
        newStudent.setDob(student.getDob());
        newStudent.setAddress(student.getAddress());
        newStudent.setDepartment(student.getDepartment());
        return newStudent;

    }
    public StudentResponseDto toResponse(Student student){
        StudentResponseDto senderdto = new StudentResponseDto();
        senderdto.setFirstName(student.getFirstName());
        senderdto.setLastName(student.getLastName());
        senderdto.setDepartment(student.getDepartment());
        senderdto.setRegid(student.getRegid());
        senderdto.setYearOfAdmission(student.getYearAdmission());
        senderdto.setPhoneNumber(student.getPhoneNumber());
        return senderdto; 
    }
}
