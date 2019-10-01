package com.assignmentevaluationportal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DivisionResponse {
	
	private Long id;
	private String name;
	private Integer capacity;
	private Integer startYear;
	private Integer endYear;
	private Long classTeacherId;
	private Long courseId;
}
