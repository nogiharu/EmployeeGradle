package com.example.employeegradle.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculationDTO {
    private String name;
    private Integer id;
    private long total;
    private double average;
    private long count;
    
    
}