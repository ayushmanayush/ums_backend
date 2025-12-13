package com.ums.backend.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ums.backend.dto.DepartmentRequestDto;
import com.ums.backend.dto.DepartmentResponseDto;
import com.ums.backend.entity.Department;
import com.ums.backend.mapper.DepartmentCreationMapper;
import com.ums.backend.mapper.DepartmentUpdateMapper;
import com.ums.backend.repository.Departmentrepository;
import com.ums.backend.exception.*;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class DepartmentService {
@Autowired
DepartmentCreationMapper departmentmapper;
@Autowired
Departmentrepository departmentrepo;
@Autowired
DepartmentUpdateMapper departmentupdatemapper;
public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentBody){
    Department department = new Department();
    department = departmentmapper.toDepartmentEntity(departmentBody);
    department.setCreatedAt(LocalDate.now());
    Department createdDepartment = departmentrepo.save(department);
    DepartmentResponseDto tosendDepartmentResponseDto = departmentmapper.toUser(createdDepartment);
    return tosendDepartmentResponseDto;
}
public List<DepartmentResponseDto> getDto(){
    List<Department> list = departmentrepo.findAll();
    List<DepartmentResponseDto> listToSend = new ArrayList<>();
    for(Department i: list){
        listToSend.add(departmentmapper.toUser(i));
    }
    return listToSend;

}
public DepartmentResponseDto updateDepartment(String departmentId,DepartmentRequestDto department){
    Department existingDepartment = departmentrepo.findById(departmentId).orElseThrow(()-> new DepartmentNotFound("Department not found with department Id: "+departmentId));
    Department updatedDepartment = departmentupdatemapper.updateExisting(department, existingDepartment);
    updatedDepartment.setCreatedAt(existingDepartment.getCreatedAt());
    updatedDepartment = departmentrepo.save(updatedDepartment);
    DepartmentResponseDto departmentTosend = departmentupdatemapper.sendUpdate(updatedDepartment);
    return departmentTosend;
}
    
public void deleteDepartment(String depId){
    departmentrepo.findById(depId).orElseThrow(()-> new DepartmentNotFound("Department not found with department Id: "+depId));
    departmentrepo.deleteById(depId);
}
}
