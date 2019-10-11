package com.assignmentevaluationportal.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.constants.Status;
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
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentDivision> studentDivisions = new ArrayList<>();
		
	@OneToOne
	@MapsId
	private User user;

	public Student(String firstName, String lastName, String email, String phoneNo, String password, String avatarUrl,
			Gender gender, String collegeFileNumber, String permanentRegistrationNumber, LocalDate admissionDate,
			LocalDate passoutDate) {
		this.user = new User(firstName, lastName, email, phoneNo, password, avatarUrl, UserStatus.NOT_VERIFIED ,gender, UserType.STUDENT);
		this.collegeFileNumber = collegeFileNumber;
		this.permanentRegistrationNumber = permanentRegistrationNumber;
		this.admissionDate = admissionDate;
		this.passoutDate = passoutDate;
	}
	
	public void addDivision(StudentDivision studentDivision) {
		this.studentDivisions.add(studentDivision);
		studentDivision.getDivision().getStudentDivisions().add(studentDivision);
	}
	
	public Division getActiveDivision() {
		StudentDivision studentDivision = getActiveStudentDivision();
		if(studentDivision != null) {
			return studentDivision.getDivision();
		}
		return null;
	}
	
	public StudentDivision getActiveStudentDivision() {
		Optional<StudentDivision> studentDivision = this.studentDivisions.stream().filter(s -> s.getStatus() == Status.ACTIVE).findFirst();
		if(studentDivision.isPresent()) {
			return studentDivision.get();
		}
		return null;
		
	}
	
	
	
}
