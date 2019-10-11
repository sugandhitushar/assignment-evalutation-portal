package com.assignmentevaluationportal.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class SubjectDTO {
	
	@NotBlank(message = "INVALID_SUBJECT_ID")
	private String id;
	
	@NotBlank(message = "INVALID_SUBJECT_NAME")
	private String name;
}
