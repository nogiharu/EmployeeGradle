package com.example.employeegradle.service;


import java.util.List;

import com.example.employeegradle.entity.Employee;
import com.example.employeegradle.entity.Task;




public interface TaskService {
	List<Task> findFlg(Integer id,boolean flg);
	List<Employee> findDistinct();
}
