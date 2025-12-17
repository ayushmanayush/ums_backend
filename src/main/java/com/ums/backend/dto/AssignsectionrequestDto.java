package com.ums.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssignsectionrequestDto {
    @NotBlank(message = "Section Should not be blank")
    private String sectionName;
}
