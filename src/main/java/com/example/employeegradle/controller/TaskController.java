package com.example.employeegradle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employeegradle.entity.Department;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.form.DepartmentForm;
import com.example.employeegradle.form.TaskForm;
import com.example.employeegradle.repository.DepartmentRepository;
import com.example.employeegradle.repository.EmployeeRepository;
import com.example.employeegradle.repository.TaskRepository;
import com.example.employeegradle.service.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskController {
	private final TaskRepository taskRepository;
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final TaskService taskService;
	private final HttpSession session;

	// 【営業課一覧を表示】
	@GetMapping("/task/{id}")
	public String showTask(@PathVariable int id, Model model) {

		List<Task> taskList = departmentRepository.getById(id).getTaskList();

		model.addAttribute("department", departmentRepository.getById(id));

		model.addAttribute("taskList", taskList);
		// 全社員リスト用のラジオボタンチェック済にするセッション all をセット
		session.setAttribute("mode", "all");

		return "taskList";
	}

	// 【社員のタスク編集画面表示】
	@GetMapping("/task/update/{employeeId}")
	public String showUpdateTask(@PathVariable int employeeId, Model model) {

		Task task = taskRepository.getById(employeeId);
		model.addAttribute("task", task);
		return "taskForm";
	}

	// 【社員のタスク更新処理】
	@PostMapping("/task/update/do")
	public String updateTask(Task task, Model model) {
		taskRepository.save(task);
		return "redirect:/task/" + task.getDepartment().getId();
	}

	

	// 【社員とタスクの削除処理】
	@GetMapping("/task/delete/{employeeId}")
	public String deleteTask(@PathVariable int employeeId, Model model) {


		Task task = taskRepository.getById(employeeId);
		task.setDeleteFlg(false);
		taskRepository.save(task);
	
		employeeRepository.deleteById(employeeId);
		return "redirect:/task/" + task.getDepartment().getId();
	}

	// 【在籍社員一覧表示】
	@GetMapping("/task/true/{id}")
	public String showTrueFlg(@PathVariable int id, Model model) {
		// 在籍社員リストを取得
		List<Task> taskList = taskService.findFlg(id, true);
		model.addAttribute("department", departmentRepository.getById(id));
		model.addAttribute("taskList", taskList);
		model.addAttribute("department", departmentRepository.getById(id));
		// 全社員リスト用のラジオボタンチェック済にするセッション true をセット
		session.setAttribute("mode", "true");
		return "taskList";
	}

	// 【退職社員一覧表示】
	@GetMapping("/task/false/{id}")
	public String showFalseFlg(@PathVariable int id, Model model) {
		// 退職社員リストを取得
		List<Task> taskList = taskService.findFlg(id, false);
		model.addAttribute("department", departmentRepository.getById(id));
		model.addAttribute("taskList", taskList);
		model.addAttribute("department", departmentRepository.getById(id));
		// 全社員リスト用のラジオボタンチェック済にするセッション false をセット
		session.setAttribute("mode", "false");
		return "taskList";
	}
}
