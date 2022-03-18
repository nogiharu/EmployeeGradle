package com.example.employeegradle.dao;

import java.util.List;

import com.example.employeegradle.entity.Employee;
import com.example.employeegradle.form.EmployeeQuery;

public interface EmployeeDAO {
    List<Employee> findByCriteria(EmployeeQuery employeeQuery);
}