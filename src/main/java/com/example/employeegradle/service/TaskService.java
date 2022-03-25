package com.example.employeegradle.service;


import java.util.List;

import com.example.employeegradle.entity.Employee;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.form.TaskForm;


public interface TaskService {
	void taskSave(Employee employee);
	List<Task> findFlg(Integer id,boolean flg);
	
}
