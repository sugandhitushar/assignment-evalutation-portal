package com.assignmentevaluationportal.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DivisionRequest {
	
	@NotBlank(message = "INVALID_DIVISION_NAME")
	String name;
	
	@NotNull(message = "INVALID_CAPACITY")
	Integer capacity;
	
	@NotNull(message = "INVALID_START_YEAR")
	Integer startYear;
	
	@NotNull(message = "INVALID_END_YEAR")
	Integer endYear;
	
	@NotNull(message = "INVALID_CLASS_TEACHER_ID")
	Long classTeacherId;
	
	@NotNull(message = "INVALID_COURSE_ID")
	Long courseId;
}
