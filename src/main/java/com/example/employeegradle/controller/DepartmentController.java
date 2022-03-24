package com.example.employeegradle.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employeegradle.dao.CalculationDAO;
import com.example.employeegradle.entity.Department;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.repository.DepartmentRepository;
import com.example.employeegradle.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

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
    public String showRegist(Task task,Model model) {
        // 新規登録用の"regist"をセッションに格納
        session.setAttribute("mode", "add");
        model.addAttribute("departmentList", departmentRepository.findAll());
        model.addAttribute("employeeList", employeeRepository.findAll());
        return "taskForm";
    }
}