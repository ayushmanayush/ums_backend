package com.ums.backend.service;
import com.ums.backend.entity.*;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ums.backend.dto.TeacherRequestDto;
import com.ums.backend.dto.TeacherResponseDto;
import com.ums.backend.mapper.TeacherCreationMapper;
import com.ums.backend.repository.Departmentrepository;
import com.ums.backend.repository.TeacherRepository;
import java.util.*;
import jakarta.transaction.Transactional;
import com.ums.backend.exception.*;
import com.ums.backend.mapper.*;

@Service
@Transactional
public class TeacherService {
    @Autowired
    TeacherCreationMapper teachermapper;
    @Autowired
    TeacherRepository teacherrepo;
    @Autowired
    TeacherUpdate teacherupdated;
    @Autowired
    Departmentrepository departmentrepo;
    public synchronized String getteacherId(){
        int value = 10000;
        int count = (int)teacherrepo.count();
        value += count + 1;
        String teach_id = String.valueOf(value);
        return teach_id; 
    }

    public TeacherResponseDto crateTeacherDto(TeacherRequestDto teacher){
        Department dept = departmentrepo.findById(teacher.getDepartment()).orElseThrow(()-> new DepartmentNotFound("Department not available with deptId: "+teacher.getDepartment()));
        Teacher newTeacher = teachermapper.toEntityDto(teacher);
        newTeacher.setTeacherId(getteacherId());
        newTeacher.setDateJoined(LocalDate.now());
        newTeacher.setDepartment(dept);
        Teacher savedTeacher = teacherrepo.save(newTeacher);
        return teachermapper.toResponseDto(savedTeacher);
    }
    public List<TeacherResponseDto> getALLTeachers(){
        List<TeacherResponseDto> listtoSend = teachermapper.toList(teacherrepo.findAll());
        return listtoSend;
    }
    public TeacherResponseDto findbyId(String reg){
        Teacher teacher = teacherrepo.findById(reg).orElseThrow(()-> new TeacherNotFoundException("Tecaher Not found with TeacherId: "+reg));
        return teachermapper.toResponseDto(teacher);
    }
    public TeacherResponseDto updateTecaherbyreg(String reg, TeacherRequestDto teacher){
        Teacher existing_teacher = teacherrepo.findById(reg).orElseThrow(()-> new TeacherNotFoundException("Teacher Not found with TeacherId: "+reg));
        Teacher updated_teacher = teacherupdated.updateTeacher(teacher, existing_teacher);
        Department dept = departmentrepo.findById(teacher.getDepartment()).orElseThrow(()-> new DepartmentNotFound("Department not available with deptId: "+teacher.getDepartment()));
        updated_teacher.setDepartment(dept);
        Teacher saved = teacherrepo.save(updated_teacher);
       return teachermapper.toResponseDto(saved);
    }
    public void deleteTeacher(String tecaherId){
        teacherrepo.findById(tecaherId).orElseThrow(()-> new TeacherNotFoundException("Teacher not found with TeacherId: "+tecaherId));
        teacherrepo.deleteById(tecaherId);
    }
}
