package com.ums.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ums.backend.entity.Student;
import java.util.List;


@Repository
public interface Studentrepository extends JpaRepository<Student,String>{
    List<Student> findByDepartmentNameDepartmentId(String departmentId);
    List<Student> findBySectionNameSectionId(String sectionId);
}