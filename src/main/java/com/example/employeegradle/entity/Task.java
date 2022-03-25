package com.example.employeegradle.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {


	@ManyToOne
	private Department department;

	@Id
	@NumberFormat
	private Integer employeeId;
	
	private String name;

	private String area;

	@NumberFormat
	private Integer sales;

	@NumberFormat
	private Integer customers;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate updateDate;

	private Boolean deleteFlg;
	
}