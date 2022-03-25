package com.example.employeegradle.form;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DepartmentForm {
    
    List<TaskForm> taskList = new ArrayList<>();

    /* public DepartmentForm(int size) {
        for (int i = 0; i < size; i++) {
            taskList.add(new TaskForm());
        }
    } */
}