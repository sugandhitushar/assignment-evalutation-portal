package com.assignmentevaluationportal.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
	INACTIVE,
	ACTIVE;
	
	@JsonValue
	public Integer getStatus() {
		return this.ordinal();
	}
}
