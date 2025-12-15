package com.ums.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ums.backend.entity.DepartmentSubject;
import com.ums.backend.entity.SubjectDepartmentId;

public interface DepartmentSubjectRepository extends JpaRepository<DepartmentSubject, SubjectDepartmentId>{

}
