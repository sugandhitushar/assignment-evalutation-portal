package com.assignmentevaluationportal.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
	INACTIVE, ACTIVE, NOT_VERIFIED;
	
	@JsonValue
	public Integer getUserStatus() {
		return this.ordinal();
	}
}
