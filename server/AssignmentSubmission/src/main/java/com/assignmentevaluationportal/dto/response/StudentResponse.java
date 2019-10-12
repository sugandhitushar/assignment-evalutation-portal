package com.assignmentevaluationportal.dto.response;

import java.time.LocalDate;
import java.time.ZoneId;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.constants.UserStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class StudentResponse extends UserResponse {
	
	private String collegeFileNumber;
	
	private String permanentRegistrationNumber;
	
	private Long admissionDate;
	
	private Long divisionId;
	
	private Integer rollNumber;

	public StudentResponse(Long id, String firstName, String lastName, String email, String phoneNo,
			String avatarUrl, Gender gender, UserStatus status, String collegeFileNumber, String permanentRegistrationNumber, LocalDate admissionDate,
			Long divisionId, Integer rollNumber) {
		super(id, firstName, lastName, email, phoneNo, avatarUrl, gender, status);
		this.collegeFileNumber = collegeFileNumber;
		this.permanentRegistrationNumber = permanentRegistrationNumber;
		this.admissionDate = admissionDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
		this.divisionId = divisionId;
		this.rollNumber = rollNumber;
	}

}
