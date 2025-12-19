package com.ums.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ums.backend.dto.SectionRequestDto;
import com.ums.backend.dto.SectionResponseDto;
import com.ums.backend.service.SectionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/sections")
public class SectionController {
    @Autowired
    SectionService sectionservice;
    @PostMapping
    public  ResponseEntity<SectionResponseDto> CreateSection(@RequestBody @Valid SectionRequestDto section){
        return ResponseEntity.status(HttpStatus.CREATED).body(sectionservice.createSection(section));
    }
}
