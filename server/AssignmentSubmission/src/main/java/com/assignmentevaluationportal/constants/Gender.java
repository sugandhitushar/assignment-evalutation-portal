package com.assignmentevaluationportal.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	MALE, FEMALE;
	
	@JsonValue
	public Integer getGender() {
		return this.ordinal();
	}
}
