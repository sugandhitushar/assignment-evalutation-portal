package com.assignmentevaluationportal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {
		
	@Id
	@Column(length = 100)
	private String id;

	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "subject",  cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DivisionSubjectTeacher> divisionSibjectTeachers = new ArrayList<>();

	public Subject(String id, String name) {
		this.id = id;
		this.name = name;
	}
}
