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
import com.assignmentevaluationportal.constants.UserType;

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
		this.user = new User(firstName, lastName, email, phoneNo, password, avatarUrl, UserStatus.ACTIVE ,gender, UserType.TEACHER);
		this.employeeId = employeeId;
		this.designation = designation;
		this.joiningDate = joiningDate;
		this.relievingDate = relievingDate;
	}
	
	

}
