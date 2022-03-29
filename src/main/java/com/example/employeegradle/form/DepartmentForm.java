package com.example.employeegradle.form;

import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Data
public class DepartmentForm {
    
        private List<Integer> department;

        private List<Integer> employeeId;

        private List<String> area;

        @NumberFormat
        private List<Integer> sales;
        @NumberFormat
        private List<Integer> customers;
        
        private List<String> updateDate;
}