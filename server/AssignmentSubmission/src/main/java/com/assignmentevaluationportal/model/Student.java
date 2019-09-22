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
@Table(name = "student")
public class Student extends BaseEntity {
	
	@Id
	private Long id;
	
	@Column(name = "college_file_number")
	private String collegeFileNumber;
	
	@Column(name = "permanent_registration_number")
	private String permanentRegistrationNumber;
	
	@Column(name = "admission_date")
	private LocalDate admissionDate;
	
	@Column(name = "passout_date")
	private LocalDate passoutDate;
	
	@OneToOne
	@MapsId
	private User user;
}
