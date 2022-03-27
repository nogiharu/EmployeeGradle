package com.example.employeegradle.form;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class DepartmentForm {
    
    List<TaskList> taskList;

    @Data
    public static class TaskList {
        private int department;

        private int employeeId;

        private String area;

        private int sales;

        private int customers;
        
        @DateTimeFormat(iso = ISO.DATE)
        private LocalDate updateDate;
    }
}