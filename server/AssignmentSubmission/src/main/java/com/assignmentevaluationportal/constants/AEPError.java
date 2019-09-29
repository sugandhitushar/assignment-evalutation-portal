package com.assignmentevaluationportal.constants;

public enum AEPError {
	
	APPLICATION_ERROR(1, "APPLICATION_ERROR"),
	INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
	
	USER_NOT_FOUND(1000, "USER_NOT_FOUND"),
	REFRESH_TOKEN_EXPIRED(1001, "REFRESH_TOKEN_EXPIRED"),
	INVALID_EMAIL_ID(1002, "INVALID_EMAIL_ID"),
	INVALID_FIRST_NAME(1003, "INVALID_FIRST_NAME"),
	INVALID_LAST_NAME(1004, "INVALID_LAST_NAME"),
	INVALID_PHONE_NO(1005, "INVALID_PHONE_NO"),
	INVALID_PASSWORD(1006, "INVALID_PASSWORD"),
	INVALID_GENDER(1007, "INVALID_GENDER"),
	INVALID_EMPLOYEE_ID(1008, "INVALID_EMPLOYEE_ID"),
	INVALID_DESIGNATION(1009, "INVALID_DESIGNATION"),
	INVALID_JOINING_DATE(1010, "INVALID_JOINING_DATE"),
	INVALID_ADMISSION_DATE(1011, "INVALID_ADMISSION_DATE"),
	INVALID_DIVISION_ID(1012, "INVALID_DIVISION_ID"),
	COURSE_ALREADY_EXISTS(1013, "COURSE_ALREADY_EXISTS"),
	INVALID_COURSE_NAME(1014, "INVALID_COURSE_NAME"),
	TEACHER_NOT_FOUND(1015, "TEACHER_NOT_FOUND"),
	COURSE_NOT_FOUND(1016, "COURSE_NOT_FOUND"),
	INVALID_DIVISION_NAME(1017, "INVALID_DIVISION_NAME"),
	INVALID_CAPACITY(1018, "INVALID_CAPACITY"),
	INVALID_START_YEAR(1019, "INVALID_START_YEAR"),
	INVALID_END_YEAR(1020, "INVALID_END_YEAR"),
	INVALID_CLASS_TEACHER_ID(1020, "INVALID_CLASS_TEACHER_ID"),
	INVALID_COURSE_ID(1021, "INVALID_COURSE_ID"),
	DIVISION_NOT_FOUND(1022, "DIVISION_NOT_FOUND");
	
	
	Integer errorCode;
	String errorMessage;
	
	private AEPError(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public static AEPError getAEPError(String aepError) {
		for(AEPError aepe: AEPError.values()) {
			if(aepError.toUpperCase().contains(aepe.name().toUpperCase())) {
				return aepe;
			}
		}
		return AEPError.APPLICATION_ERROR;
	}
	
	
}
