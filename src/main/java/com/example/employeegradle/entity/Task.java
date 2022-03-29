package com.example.employeegradle.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import org.springframework.format.annotation.NumberFormat;


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

	private String updateDate;

	private Boolean deleteFlg;
}