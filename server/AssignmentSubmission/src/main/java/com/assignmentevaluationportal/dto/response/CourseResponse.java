package com.assignmentevaluationportal.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CourseResponse {

	private Long id;
	
	private String name;

	public CourseResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
