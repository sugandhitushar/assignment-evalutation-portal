package com.assignmentevaluationportal.dto.response;

import java.time.LocalDate;
import java.time.ZoneId;

import com.assignmentevaluationportal.constants.Gender;

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

	public StudentResponse(Long id, String firstName, String lastName, String email, String phoneNo,
			String avatarUrl, Gender gender, String collegeFileNumber, String permanentRegistrationNumber, LocalDate admissionDate,
			Long divisionId) {
		super(id, firstName, lastName, email, phoneNo, avatarUrl, gender);
		this.collegeFileNumber = collegeFileNumber;
		this.permanentRegistrationNumber = permanentRegistrationNumber;
		this.admissionDate = admissionDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
		this.divisionId = divisionId;
	}

}
