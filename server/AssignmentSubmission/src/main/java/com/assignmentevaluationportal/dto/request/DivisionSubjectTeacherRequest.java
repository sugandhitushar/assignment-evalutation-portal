package com.assignmentevaluationportal.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DivisionSubjectTeacherRequest {
	
	@NotBlank(message = "INVALID_SUBJECT_ID")
	private String subjectId;
	
	@NotNull(message = "INVALID_TEACHER_ID")
	private Long teacherId;
}
