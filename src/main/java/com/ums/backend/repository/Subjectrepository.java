package com.ums.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ums.backend.entity.Subject;
@Repository
public interface Subjectrepository extends JpaRepository<Subject, String>{
}