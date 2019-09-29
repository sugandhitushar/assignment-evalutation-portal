package com.assignmentevaluationportal.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TeacherRequest extends UserRequest {

	@NotBlank(message = "INVALID_EMPLOYEE_ID")
	private String employeeId;
	
	@NotBlank(message = "INVALID_DESIGNATION")
	private String designation;
	
	@NotNull(message = "INVALID_JOINING_DATE")
	private Long joiningDate;
}
