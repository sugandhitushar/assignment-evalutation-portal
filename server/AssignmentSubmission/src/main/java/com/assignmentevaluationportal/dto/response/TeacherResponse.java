package com.assignmentevaluationportal.dto.response;

import java.time.LocalDate;
import java.time.ZoneId;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.constants.UserStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class TeacherResponse extends UserResponse {
	
	private String employeeId;
	
	private String designation;
	
	private Long joiningDate;

	public TeacherResponse(Long id, String firstName, String lastName, String email, String phoneNo,
			String avatarUrl, Gender gender, UserStatus status, String employeeId, String designation, LocalDate joiningDate) {
		super(id, firstName, lastName, email, phoneNo, avatarUrl, gender, status);
		this.employeeId = employeeId;
		this.designation = designation;
		this.joiningDate = joiningDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
	
	
}
