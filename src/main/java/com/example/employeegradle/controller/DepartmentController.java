package com.example.employeegradle.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employeegradle.dao.CalculationDAO;
import com.example.employeegradle.entity.Department;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.form.DepartmentForm;
import com.example.employeegradle.form.TaskForm;
import com.example.employeegradle.repository.DepartmentRepository;
import com.example.employeegradle.repository.EmployeeRepository;
import com.example.employeegradle.service.TaskService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskService taskService;

    private final HttpSession session;
    private final CalculationDAO calcDAO;

    @GetMapping("/department")
    public String showDepartment(Model model, Department department) {
       // 念のためsession破棄
        session.invalidate();
        model.addAttribute("calcList", calcDAO.findByCriteria());
        return "departmentList";
    }

    @PostMapping("/department/regist")
    public String registDepartment(Department department, Model model) {
        departmentRepository.save(department);
        return "redirect:/department";
    }

    // 新規登録画面表示
    @GetMapping("/department/add")
    public String showRegist(Model model,DepartmentForm  departmentForm) {
        // 新規登録用の"regist"をセッションに格納
        departmentForm.getTaskList().add(new TaskForm());
        model.addAttribute("departmentForm", departmentForm);
        session.setAttribute("mode", "add");
        model.addAttribute("departmentList", departmentRepository.findAll());
        model.addAttribute("employeeList", employeeRepository.findAll());
        return "taskAddForm";
    }

    // 【社員のタスク追加処理】
	@PostMapping(value = "/task/add",params = "add")
	public String addTask(Department department, Model model) {
		department.addList();
    
		
		//taskList.add(task);
		return "taskAddForm";
	}
}