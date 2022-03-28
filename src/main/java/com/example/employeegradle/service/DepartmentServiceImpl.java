package com.example.employeegradle.service;

import java.util.Optional;

import com.example.employeegradle.entity.Department;
import com.example.employeegradle.entity.Task;
import com.example.employeegradle.form.DepartmentForm;
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

        Optional<DepartmentForm> op = Optional.of(departmentForm);
        op.ifPresent(x -> {
            for (int i = 0; i < x.getDepartment().size(); i++) {
                Department department = departmentRepository.findById(x.getDepartment().get(i)).get();
                String name = employeeRepository.findById(x.getEmployeeId().get(i)).get().getName();
                Task task = new Task(department, x.getEmployeeId().get(i), name, x.getArea().get(i), 
                        x.getSales().get(i), x.getCustomers().get(i), x.getUpdateDate().get(i), true);
                taskRepository.save(task);
            }
        });
    }
}