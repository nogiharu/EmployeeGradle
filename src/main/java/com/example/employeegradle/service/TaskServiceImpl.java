package com.example.employeegradle.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


import com.example.employeegradle.entity.Task;
import com.example.employeegradle.repository.DepartmentRepository;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final DepartmentRepository departmentRepository;

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


}
