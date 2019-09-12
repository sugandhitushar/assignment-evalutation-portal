package com.assignmentevaluationportal.constants;

public class ApiUrl {
	
	public static final String API_BASE = "/api";
	public static final String VERSION_1 =  "/v1";
	public static final String BASE_URL_V1 = API_BASE + VERSION_1;
	public static final String LOGIN_ENDPOINT = "/login";
	public static final String SIGNUP_ENDPOINT = "/signup";
	public static final String HELLO_ENDPOINT = "/hello";
	public static final String TEACHER_ENDPOINT = "/teachers";
	public static final String STUDENT_ENDPOINT = "/students";
	public static final String TEACHER_SIGNUP_ENDPOINT = TEACHER_ENDPOINT + SIGNUP_ENDPOINT;
	public static final String STUDENT_SIGNUP_ENDPOINT = STUDENT_ENDPOINT + SIGNUP_ENDPOINT;
	

}
