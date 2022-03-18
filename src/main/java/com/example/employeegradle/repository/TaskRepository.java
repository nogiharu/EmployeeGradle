package com.example.employeegradle.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.employeegradle.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
