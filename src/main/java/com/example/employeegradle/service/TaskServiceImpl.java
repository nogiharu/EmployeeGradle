package com.example.employeegradle.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.employeegradle.entity.Employee;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.repository.DepartmentRepository;
import com.example.employeegradle.repository.EmployeeRepository;
import com.example.employeegradle.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final TaskRepository taskRepository;

	@Override
	public List<Task> findFlg(Integer id, boolean flg) {
		// 該当課の社員リストを取得
		List<Task> taskList = departmentRepository.getById(id).getTaskList();
		// 格納リストを準備
		List<Task> taskFlgList = new ArrayList<>();

		// flgが true なら => DeleteFlgを true のみ指定 => リストに追加
		taskList.stream().filter(t -> flg).filter(t -> t.getDeleteFlg()).forEach(taskFlgList::add);
		// flgが false なら => DeleteFlgを false のみ指定 => リストに追加
		taskList.stream().filter(f -> !flg).filter(f -> !f.getDeleteFlg()).forEach(taskFlgList::add);

		return taskFlgList;
	}

	@Override
	public List<Employee> findDistinct() {

		List<Employee> employeeList = employeeRepository.findAll();
		List<Task> taskList = taskRepository.findAll();
		// タスクIDと社員IDが一致していないもののみ社員を抽出
		List<Employee> employees = employeeList.stream().filter(emp -> taskList.stream()
				.noneMatch(task -> emp.getId() == task.getEmployeeId() ))
				.collect(Collectors.toList());

		return employees;
	}
}
