package com.assignmentevaluationportal.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
	TEACHER, STUDENT;
	
	@JsonValue
	public Integer getUserType() {
		return this.ordinal();
	}
}
