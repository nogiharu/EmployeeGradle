package com.example.employeegradle.service;


import java.util.List;

import com.example.employeegradle.entity.Department;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.form.DepartmentForm;
import com.example.employeegradle.form.DepartmentForm.TaskList;
import com.example.employeegradle.repository.DepartmentRepository;
import com.example.employeegradle.repository.EmployeeRepository;
import com.example.employeegradle.repository.TaskRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DeaprtmentService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final TaskRepository taskRepository;

    @Override
    public void departmentAdd(DepartmentForm departmentForm) {

        List<TaskList> taskList = departmentForm.getTaskList();

        taskList.stream().forEach(t -> {
            // 課取得
            Department department = departmentRepository.findById(t.getDepartment()).get();
            // 社員名取得
            if (t.getEmployeeId() > 0) {
                String name = employeeRepository.findById(t.getEmployeeId()).get().getName();
            // Taskセーブ
            Task task = new Task(department, t.getEmployeeId(), name,
                    t.getArea(), t.getSales(), t.getCustomers(), t.getUpdateDate(), true);
            taskRepository.save(task);
            }
        });

    }
}