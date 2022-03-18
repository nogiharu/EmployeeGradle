package com.example.employeegradle.form;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


import com.example.employeegradle.entity.Employee;
import com.example.employeegradle.valition.Groups.First;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeForm {
    
    @NotBlank
    private String name;

    @NotNull
    private Integer gender;

    @NotBlank
    private String password;

    @NotBlank
    private String rePassword;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}-[0-9]{4}+$")
    private String phone;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate registDate;


    @AssertTrue(groups = First.class)
    public boolean isValidPassword() {
        if (password == null || password.isEmpty()) {
            return true;
        }
        return password.equals(rePassword);
    }

    public Employee toEntity() {
        Employee emp = new Employee();
        emp.setEmail(email);
        emp.setGender(gender);
        emp.setName(name);
        emp.setPassword(password);
        emp.setPhone(phone);
        emp.setRegistDate(Date.valueOf(registDate));
        return emp;
    }

    public EmployeeForm(Employee employee) {
        setName(employee.getName());
        setGender(employee.getGender());
        setEmail(employee.getEmail());
        setPassword(employee.getPassword());
        setPhone(employee.getPhone());
        setRegistDate(employee.getRegistDate().toLocalDate());

    }


}