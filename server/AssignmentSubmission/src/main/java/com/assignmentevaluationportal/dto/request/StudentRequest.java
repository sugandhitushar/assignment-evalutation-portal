package com.assignmentevaluationportal.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class StudentRequest extends UserRequest {

	private String collegeFileNumber;

	private String permanentRegistrationNumber;

	private Long admissionDate;

	@NotNull(message = "INVALID_DIVISION_ID")
	private Long divisionId;
	
	@NotNull(message = "INVALID_ROLL_NUMBER")
	private Integer rollNumber;

}
