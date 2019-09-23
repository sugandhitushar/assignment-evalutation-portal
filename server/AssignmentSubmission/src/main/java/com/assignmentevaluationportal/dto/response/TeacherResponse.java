package com.assignmentevaluationportal.dto.response;

import java.time.LocalDate;

import com.assignmentevaluationportal.constants.Gender;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TeacherResponse extends UserResponse {
	
	private String employeeId;
	
	private String designation;
	
	private LocalDate joiningDate;

	public TeacherResponse(Long id, String firstName, String lastName, String email, String phoneNo,
			String avatarUrl, Gender gender, String employeeId, String designation, LocalDate joiningDate) {
		super(id, firstName, lastName, email, phoneNo, avatarUrl, gender);
		this.employeeId = employeeId;
		this.designation = designation;
		this.joiningDate = joiningDate;
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
	
	
	
}
