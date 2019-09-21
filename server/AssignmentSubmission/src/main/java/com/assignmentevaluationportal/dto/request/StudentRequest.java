package com.assignmentevaluationportal.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class StudentRequest  extends UserRequest {
	
	private String collegeFileNumber;
	
	private String permanentRegistrationNumber;
	
	@PastOrPresent
	private LocalDate admissionDate;
	
	@NotNull
	private Long divisionId;

}