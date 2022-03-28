package com.example.employeegradle.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employeegradle.dao.CalculationDAO;
import com.example.employeegradle.entity.Department;
import com.example.employeegradle.form.DepartmentForm;

import com.example.employeegradle.repository.DepartmentRepository;
import com.example.employeegradle.service.DeaprtmentService;
import com.example.employeegradle.service.TaskService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final DeaprtmentService deaprtmentService;
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
        
        // タスクを登録した社員は「departmentAdd.html」の「select」タグに表示しない。
        model.addAttribute("employeeList", taskService.findDistinct());
        model.addAttribute("departmentList", departmentRepository.findAll());
        return "departmentAdd";
    }

    // 【社員のタスク追加処理】
	@PostMapping("/department/add")
    public String addTask(@Validated DepartmentForm departmentForm,BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("employeeList", taskService.findDistinct());
            model.addAttribute("departmentList", departmentRepository.findAll());
            return "departmentAdd";
        }
        deaprtmentService.departmentAdd(departmentForm);
		return "redirect:/department";
	}
}