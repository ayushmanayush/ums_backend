package com.ums.backend.service;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.entity.*;
import com.ums.backend.repository.*;
import java.util.*;

import com.ums.backend.exception.DepartmentNotFound;
import com.ums.backend.exception.SectionNotFound;
import com.ums.backend.exception.StudentnotFound;
import com.ums.backend.mapper.StudentCreationMapper;
import com.ums.backend.dto.*;

@Service
@Transactional
public class StudentService{

  @Autowired
  private Studentrepository studentrepo;
  @Autowired 
  private StudentCreationMapper studentmapper ;
  @Autowired
  Departmentrepository departmentrepo;
  @Autowired
  SectionRepository sectionrepo;
  @Autowired
  TeachingAssignmentRepository teachingAssignmentRepo;

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
    Department dept = departmentrepo.findById(stud.getDepartment()).orElseThrow(()-> new DepartmentNotFound("please Enter valid Department!"));
    newStudent.setDepartmentName(dept);
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


  public StudentResponseDto updateStudent(String reg, StudentRequestDto stuobj){
    Student obj = studentrepo.findById(reg).orElseThrow(() -> new StudentnotFound("Student Not found with regId: "+reg));
    obj.setFirstName(stuobj.getFirstName());
    obj.setLastName(stuobj.getLastName());
    obj.setPhoneNumber(stuobj.getPhoneNumber());
    obj.setDepartmentName(departmentrepo.findById(stuobj.getDepartment()).orElseThrow(()-> new DepartmentNotFound("Please Enter valid Department !")));
    obj.setAddress(stuobj.getAddress());
    obj.setDob(stuobj.getDob());
    obj.setEmail(stuobj.getEmail());
    Student stud = studentrepo.save(obj);
    StudentResponseDto student = studentmapper.toResponse(stud);
    return student;
  }

  public String UpdatePatch(String regId, StudentRequestDto updatedValue){
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
      Department dept = departmentrepo.findById(updatedValue.getDepartment()).orElseThrow(()-> new DepartmentNotFound("Please Enter Valid department!"));
      existingValue.setDepartmentName(dept);
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
  public StudentResponseDto updateSection(String regid, AssignsectionrequestDto section){
    Student student = studentrepo.findById(regid).orElseThrow(()-> new StudentnotFound("Student not found with regId: "+regid));
    Section sect = sectionrepo.findById(section.getSectionName()).orElseThrow(()-> new SectionNotFound("Section is not Created Yet!"));
    student.setSectionName(sect);
    student = studentrepo.save(student);
    return studentmapper.toResponse(student);
  }
  public List<StudentResponseDto> getStudentBySection(String sect){
    List<Student> list = studentrepo.findBySectionNameSectionId(sect);
    List<StudentResponseDto> listToSend = new ArrayList<>();
    for(Student i : list){
      listToSend.add(studentmapper.toResponse(i));
    }
    return listToSend;
  }
  public List<StudentResponseDto> getstudentsByDepartment(String depId){
    List<Student> list = studentrepo.findByDepartmentNameDepartmentId(depId);
    List<StudentResponseDto> listToSend = new ArrayList<>();
    for(Student i: list){
      listToSend.add(studentmapper.toResponse(i));
    }
    return listToSend;
  }
  public List<SubjectResponseDto> getSubjectsForStudent(String regId) {

    Student student = studentrepo.findById(regId)
        .orElseThrow(() -> new StudentnotFound("Student not found"));

    String sectionId = student.getSectionName().getSectionId();

    List<TeachingAssignment> assignments =
        teachingAssignmentRepo.findBySection_sectionId(sectionId);

    List<SubjectResponseDto> subjects = new ArrayList<>();

    for (TeachingAssignment ta : assignments) {
        Subject s = ta.getSubject();

        SubjectResponseDto dto = new SubjectResponseDto();
        dto.setSubjectId(s.getSubjectId());
        dto.setSubjectName(s.getSubjectName());
        dto.setCredit(s.getCredit());

        subjects.add(dto);
    }

    return subjects;
}
public List<TeacherResponseDto> getTeachersForStudent(String regId) {

    Student student = studentrepo.findById(regId)
        .orElseThrow(() -> new StudentnotFound("Student not found"));

    String sectionId = student.getSectionName().getSectionId();

    List<TeachingAssignment> assignments =
        teachingAssignmentRepo.findBySection_sectionId(sectionId);

    List<TeacherResponseDto> teachers = new ArrayList<>();

    for (TeachingAssignment ta : assignments) {
        Teacher t = ta.getTeacher();

        TeacherResponseDto dto = new TeacherResponseDto();
        dto.setTeacherId(t.getTeacherId());
        dto.setFirstName(t.getFirstName());
        dto.setLastName(t.getLastName());

        teachers.add(dto);
    }

    return teachers;
}
}
