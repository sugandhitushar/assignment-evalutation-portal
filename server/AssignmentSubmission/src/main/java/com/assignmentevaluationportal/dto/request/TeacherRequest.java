package com.assignmentevaluationportal.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TeacherRequest extends UserRequest {

	@NotBlank
	private String employeeId;
	
	@NotBlank
	private String designation;
	
	@NotNull
	private LocalDate joiningDate;
}
