package com.assignmentevaluationportal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher extends BaseEntity {
	
	@Id
	private Long id;
	
	@Column(name = "employee_id")
	private String employeeId;
	
	private String designation;

	@Column(name = "joining_date")
	private LocalDate joiningDate;

	@Column(name = "relieving_date")
	private LocalDate relievingDate;
	
	@OneToOne
	@MapsId
	private User user;

}
