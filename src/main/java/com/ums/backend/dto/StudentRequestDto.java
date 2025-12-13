package com.ums.backend.dto;

import java.time.LocalDate;

// import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
// import lombok.Data;
// @Data
public class StudentRequestDto {
    @NotBlank(message = "First name should not be null")
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Email(message ="Please Enter a valid email")
    @NotBlank(message = "Email should not be blank")
    private String email;
    @NotBlank(message = "Address should not bhe null")
    private String address;
    @NotBlank(message = "DoB Should not be null")
    private LocalDate dob;
    @NotBlank(message = "Department should not be null")
    private String department;
    

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setDepartment(String department){
        this.department = department;
    }
    public String getDepartment(){
        return department;
    }

    public void setDob(LocalDate dob){
        this.dob = dob;
    }
    public LocalDate getDob(){
        return dob;
    }

}
