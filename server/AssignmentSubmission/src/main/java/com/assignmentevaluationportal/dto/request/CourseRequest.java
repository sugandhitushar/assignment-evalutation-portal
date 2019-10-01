package com.assignmentevaluationportal.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CourseRequest {
	
	@NotBlank(message = "INVALID_COURSE_NAME")
	private String name;

}
