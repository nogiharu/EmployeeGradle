package com.example.employeegradle.service;

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
    public void departmentAdd(DepartmentForm df) {

        for (int i = 0; i < df.getDepartment().size(); i++) {
            // 部署
            Department department = departmentRepository.findById(df.getDepartment().get(i)).get();
            // 社員名
            String name = employeeRepository.findById(df.getEmployeeId().get(i)).get().getName();
            // 社員ID
            int employeeId = df.getEmployeeId().get(i);
            
            // 【以下入力値がなければ空文字か0をセット】
            // 担当地区
            String area;
            try {
                area = df.getArea().get(i);
            } catch (IndexOutOfBoundsException e) {
                area = "";
            }
            // 売上
            int sales;
            try {
                sales = df.getSales().get(i);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                sales = 0;
            }
            // 顧客数
            int customers;
            try {
                customers = df.getCustomers().get(i);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                customers = 0;
            }
            // 更新日
            String updateDate;
            try {
                updateDate = df.getUpdateDate().get(i);
            } catch (IndexOutOfBoundsException e) {
                updateDate = "";
            }
            Task task = new Task(department, employeeId, name, area,
                    sales, customers, updateDate, true);
            taskRepository.save(task);
        }
    }
}