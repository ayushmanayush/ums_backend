package com.ums.backend.service;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ums.backend.entity.*;
import com.ums.backend.repository.*;
import java.util.*;


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
    return studentrepo.findById(reg).orElse(null);
}
}
