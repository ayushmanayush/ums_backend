package com.ums.backend.service;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ums.backend.entity.*;
import com.ums.backend.repository.*;
import java.util.*;
import com.ums.backend.exception.StudentnotFound;


@Service
public class StudentService{
  @Autowired
  private Studentrepository studentrepo;
  public Student createStudent(Student student){
    String reg_year = String.valueOf(LocalDate.now().getYear());
    int year = (Integer.parseInt(reg_year.substring(2)))*1000;
    int count_student = (int)studentrepo.count();
    year += count_student + 1;
    String reg = "1" + String.valueOf(year);
    student.setRegid(reg);
    student.setYearAdmission(reg_year);
    return studentrepo.save(student);
  }


  public List<Student> getallstudents(){
    return studentrepo.findAll();
  }


  public Student findStudent(String reg){
    return studentrepo.findById(reg).orElseThrow(() -> new StudentnotFound("Student not found with regId: "+reg));
}


  public Student updateStudent(String reg, Student stuobj){
    Student obj = studentrepo.findById(reg).orElseThrow(() -> new StudentnotFound("Student Not found with regId: "+reg));
    obj.setFirstName(stuobj.getFirstName());
    obj.setLastName(stuobj.getLastName());
    obj.setPhoneNumber(stuobj.getPhoneNumber());
    obj.setDepartment(stuobj.getDepartment());
    obj.setAddress(stuobj.getAddress());
    obj.setDob(stuobj.getDob());
    obj.setEmail(stuobj.getEmail());
    studentrepo.save(obj);
    return studentrepo.findById(reg).orElseThrow(()-> new StudentnotFound("Student not found with regId: "+reg));
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

  public String deleteStudent(String regId){
      studentrepo.findById(regId).orElseThrow(() ->new StudentnotFound("Student not found with regId: "+regId));
      studentrepo.deleteById(regId);
    return "Student deleted from database with regID: "+regId; 
  }
}
