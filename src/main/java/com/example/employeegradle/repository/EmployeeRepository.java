package com.example.employeegradle.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeegradle.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
}