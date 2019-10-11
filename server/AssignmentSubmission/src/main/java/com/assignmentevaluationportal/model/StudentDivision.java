package com.assignmentevaluationportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.assignmentevaluationportal.constants.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
public class StudentDivision extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "division_id")
	private Division division;
	
	@Column(name = "roll_number")
	private Integer rollNumber;
	
	private Status status;

	public StudentDivision(Student student, Division division, Integer rollNumber) {
		this.student = student;
		this.division = division;
		this.rollNumber = rollNumber;
		this.status = Status.ACTIVE;
	}
	
	

}
