package com.assignmentevaluationportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.assignmentevaluationportal.constants.Status;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
@Table(name = "division")
public class Division extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Integer capacity;
	
	@Column(name = "start_year")
	private Integer startYear;
	
	@Column(name = "end_year")
	private Integer endYear;
	
	private Status status;;
	
	@OneToOne
	@JoinColumn(name = "class_teacher_id", referencedColumnName = "user_id")
	private Teacher classTeacher;
	
	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "id")
	private Course course;

}
