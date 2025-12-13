package com.ums.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ums.backend.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section,String>{

    
}
