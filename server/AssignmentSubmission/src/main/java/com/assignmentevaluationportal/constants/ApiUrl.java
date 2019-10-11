package com.assignmentevaluationportal.constants;

public class ApiUrl {
	
	public static final String API_BASE = "/api";
	public static final String VERSION_1 =  "/v1";
	public static final String BASE_URL_V1 = API_BASE + VERSION_1;
	public static final String LOGIN = "/login";
	public static final String REFRESH_TOKEN = "/token/refresh";
	public static final String SIGNUP = "/signup";
	public static final String HELLO = "/hello";
	public static final String TEACHERS = "/teachers";
	public static final String STUDENTS = "/students";
	public static final String USERS = "/users";
	public static final String COURSES =  "/courses";
	public static final String DIVISIONS = "/divisions";
	public static final String DIVISION_BY_COURSE_ID = COURSES + "/{courseId}" + DIVISIONS;
	public static final String TEACHER_SIGNUP = TEACHERS + SIGNUP;
	public static final String STUDENT_SIGNUP = STUDENTS + SIGNUP;
	public static final String TEACHER_BY_ID = TEACHERS + "/{id}";
	public static final String ALL_STUDENTS_OF_DIVISION = DIVISIONS + "/{divisionId}" + STUDENTS;
	public static final String SUBJECTS = "/subjects";
	public static final String DIVISION_SUBJECTS = DIVISIONS + "/{divisionId}" + SUBJECTS;
	public static final String VERIFY_USER = BASE_URL_V1 + USERS + "/{id}/verify";
	public static final String REJECT_USER = BASE_URL_V1 + USERS + "/{id}/reject";
	

}
