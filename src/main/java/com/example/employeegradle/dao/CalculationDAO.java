package com.example.employeegradle.dao;

import java.util.List;

import com.example.employeegradle.dto.CalculationDTO;

public interface CalculationDAO {

    List<CalculationDTO> findByCriteria();
    List<CalculationDTO> findByJPQL();
}