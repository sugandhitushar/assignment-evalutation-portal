package com.assignmentevaluationportal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.constants.UserStatus;

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

	public Teacher(String firstName, String lastName, String email, String phoneNo, String password, String avatarUrl,
			Gender gender, String employeeId, String designation, LocalDate joiningDate, 
			LocalDate relievingDate) {
		this.user = new User(firstName, lastName, email, phoneNo, password, avatarUrl, UserStatus.ACTIVE ,gender);
		this.employeeId = employeeId;
		this.designation = designation;
		this.joiningDate = joiningDate;
		this.relievingDate = relievingDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDate getRelievingDate() {
		return relievingDate;
	}

	public void setRelievingDate(LocalDate relievingDate) {
		this.relievingDate = relievingDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
