package com.example.employeegradle.dao;

import com.example.employeegradle.entity.Task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskDAO {
    Page<Task> findTaskAll(int id,Boolean flg,Pageable pageable);
}