package com.example.employeegradle.controller;


import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.employeegradle.dao.TaskDAO;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.repository.DepartmentRepository;
import com.example.employeegradle.repository.EmployeeRepository;
import com.example.employeegradle.repository.TaskRepository;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskController {
	private final TaskRepository taskRepository;
	private final DepartmentRepository departmentRepository;
	private final EmployeeRepository employeeRepository;
	private final TaskDAO taskDAO;
	private final HttpSession session;

	// 【営業課一覧を表示】
	@GetMapping("/task/{id}")
	public String showTask(@PathVariable int id,@PageableDefault(size = 5) Pageable pageable, Model model) {
		Page<Task> taskPages = taskDAO.findTaskAll(id, null, pageable);
		model.addAttribute("department", departmentRepository.getById(id));
		model.addAttribute("taskList", taskPages);
		session.setAttribute("mode", "all");
		return "taskList";
	}
	// 【在籍社員一覧表示】
	@GetMapping("/task/true/{id}")
	public String showTrueFlg(@PathVariable int id, @PageableDefault(size = 5) Pageable pageable, Model model) {
		// 在籍社員リストを取得
		Page<Task> taskPages = taskDAO.findTaskAll(id, true, pageable);
		model.addAttribute("department", departmentRepository.getById(id));
		model.addAttribute("taskList", taskPages);
		session.setAttribute("mode", true);
		return "taskList";
	}

	// 【退職社員一覧表示】
	@GetMapping("/task/false/{id}")
	public String showFalseFlg(@PathVariable int id, @PageableDefault(size = 5) Pageable pageable, Model model) {
		// 退職社員リストを取得
		Page<Task> taskPages = taskDAO.findTaskAll(id, false, pageable);
		model.addAttribute("department", departmentRepository.getById(id));
		model.addAttribute("taskList", taskPages);
		session.setAttribute("mode", false);
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
}
