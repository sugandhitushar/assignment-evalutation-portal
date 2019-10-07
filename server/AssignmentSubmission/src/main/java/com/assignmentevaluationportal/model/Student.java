package com.assignmentevaluationportal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "division_id", referencedColumnName = "id")
	private Division division;
	
	@OneToOne
	@MapsId
	private User user;

	public Student(String firstName, String lastName, String email, String phoneNo, String password, String avatarUrl,
			Gender gender, String collegeFileNumber, String permanentRegistrationNumber, LocalDate admissionDate,
			LocalDate passoutDate, Division division) {
		this.user = new User(firstName, lastName, email, phoneNo, password, avatarUrl, UserStatus.ACTIVE ,gender, UserType.STUDENT);
		this.collegeFileNumber = collegeFileNumber;
		this.permanentRegistrationNumber = permanentRegistrationNumber;
		this.admissionDate = admissionDate;
		this.passoutDate = passoutDate;
		this.division = division;
	}
	
	
	
	
}
