package com.assignmentevaluationportal.model;

import javax.persistence.CascadeType;
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
public class DivisionSubjectTeacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "division_id")
	private Division division;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	private Status status;

	public DivisionSubjectTeacher(Division division, Subject subject, Teacher teacher) {
		this.division = division;
		this.subject = subject;
		this.teacher = teacher;
		this.status = Status.ACTIVE;
		
		this.division.getDivisionSubjectTeachers().add(this);
		this.subject.getDivisionSibjectTeachers().add(this);
		this.teacher.getDivisionSubjectTeachers().add(this);
	}
	
	

}
