package com.ums.backend.service;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.entity.*;
import com.ums.backend.repository.*;
import java.util.*;
import com.ums.backend.exception.StudentnotFound;
import com.ums.backend.mapper.StudentCreationMapper;
import com.ums.backend.dto.*;

@Service
@Transactional
public class StudentService{

  @Autowired
  private Studentrepository studentrepo;
  @Autowired StudentCreationMapper studentmapper ;


  public synchronized String generateRegId(){
      String reg_year = String.valueOf(LocalDate.now().getYear());
    int year = (Integer.parseInt(reg_year.substring(2)))*1000;
    int count_student = (int)studentrepo.count();
    year += count_student + 1;
    String reg = "1" + String.valueOf(year);
    return reg;
  }
  public StudentResponseDto createStudentDto(StudentRequestDto stud){

    Student newStudent = studentmapper.toEntity(stud);
    String reg = generateRegId();
    newStudent.setRegid(reg);
    String reg_year =String.valueOf( LocalDate.now().getYear());
    newStudent.setYearAdmission(reg_year);
    Student savedStudent =  studentrepo.save(newStudent);
    StudentResponseDto resdto = studentmapper.toResponse(savedStudent);
    return resdto;
  }


  public List<StudentResponseDto> getallstudents(){
    List<Student> student = studentrepo.findAll();
    List<StudentResponseDto> list = new ArrayList<>();
    for(Student stud : student){
      list.add(studentmapper.toResponse(stud));
    }
    return list;
  }


  public StudentResponseDto findStudent(String reg){
    Student stud = studentrepo.findById(reg).orElseThrow(() -> new StudentnotFound("Student not found with regId: "+reg));
    StudentResponseDto student =studentmapper.toResponse(stud);
    return student;
}


  public StudentResponseDto updateStudent(String reg, Student stuobj){
    Student obj = studentrepo.findById(reg).orElseThrow(() -> new StudentnotFound("Student Not found with regId: "+reg));
    obj.setFirstName(stuobj.getFirstName());
    obj.setLastName(stuobj.getLastName());
    obj.setPhoneNumber(stuobj.getPhoneNumber());
    obj.setDepartment(stuobj.getDepartment());
    obj.setAddress(stuobj.getAddress());
    obj.setDob(stuobj.getDob());
    obj.setEmail(stuobj.getEmail());
    Student stud = studentrepo.save(obj);
    StudentResponseDto student = studentmapper.toResponse(stud);
    return student;
  }

  public String UpdatePatch(String regId, Student updatedValue){
    Student existingValue = studentrepo.findById(regId).orElseThrow(()-> new StudentnotFound("Student not Found with regId: "+ regId)); 
    if(updatedValue.getFirstName() != null){
      existingValue.setFirstName(updatedValue.getFirstName());
    }
    if(updatedValue.getLastName() != null){
      existingValue.setLastName(updatedValue.getLastName());
    }
    if(updatedValue.getPhoneNumber() != null){
      existingValue.setPhoneNumber(updatedValue.getPhoneNumber());
    }
    if(updatedValue.getEmail() != null){
      existingValue.setEmail(updatedValue.getEmail());
    }
    if(updatedValue.getAddress() != null){
      existingValue.setAddress(updatedValue.getAddress());
    }
    if(updatedValue.getDepartment() != null){
      existingValue.setDepartment(updatedValue.getDepartment());
    }
    if(updatedValue.getDob() != null){
      existingValue.setDob(updatedValue.getDob());
    }
    studentrepo.save(existingValue);
    return "Student details updated Successfully with regId "+regId;
  }

  public void deleteStudent(String regId){
      studentrepo.findById(regId).orElseThrow(()-> new StudentnotFound("Student not found with regId "+regId));
      studentrepo.deleteById(regId); 
  }
}
