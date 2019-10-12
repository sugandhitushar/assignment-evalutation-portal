package com.assignmentevaluationportal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentevaluationportal.constants.ApiUrl;
import com.assignmentevaluationportal.dto.request.StudentRequest;
import com.assignmentevaluationportal.dto.response.Response;
import com.assignmentevaluationportal.dto.response.StudentResponse;
import com.assignmentevaluationportal.model.Student;
import com.assignmentevaluationportal.service.StudentService;

@RestController
@RequestMapping(ApiUrl.BASE_URL_V1)

public class StudentController {
	
	private final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping(ApiUrl.STUDENT_SIGNUP)
	public ResponseEntity<?> signup(@Validated @RequestBody StudentRequest request) {
		logger.info("Student Signup API request: {}", request);
		
		Student student = studentService.signup(request.getFirstName(), request.getLastName(), 
				request.getEmail(), request.getPhoneNo(), request.getPassword(), request.getAvatarUrl(), 
				request.getGender(), request.getCollegeFileNumber(), request.getPermanentRegistrationNumber(), 
				request.getAdmissionDate(), request.getDivisionId(), request.getRollNumber());
		
		StudentResponse response = new StudentResponse(student.getId(), student.getUser().getFirstName(), 
				student.getUser().getLastName(), student.getUser().getEmail(), student.getUser().getPhoneNo(), 
				student.getUser().getAvatarUrl(), student.getUser().getGender(), student.getUser().getStatus(), 
				student.getCollegeFileNumber(), student.getPermanentRegistrationNumber(), student.getAdmissionDate(), 
				student.getActiveDivision() != null ? student.getActiveDivision().getId() : null, 
				student.getActiveStudentDivision() != null ? student.getActiveStudentDivision().getRollNumber() : null);
		
		logger.info("Student Signup API response: {}", response);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.<StudentResponse>getSuccessResponse(response));		
	}
	
	@GetMapping(ApiUrl.STUDENTS)
	public ResponseEntity<?> getAllStudents(){
		logger.info("Get all students API request");
		
		List<StudentResponse> response = studentService.getAllStudents().stream()
				.map(student -> new StudentResponse(student.getId(), student.getUser().getFirstName(), 
					student.getUser().getLastName(), student.getUser().getEmail(), student.getUser().getPhoneNo(), 
					student.getUser().getAvatarUrl(), student.getUser().getGender(), student.getUser().getStatus(), 
					student.getCollegeFileNumber(), student.getPermanentRegistrationNumber(), student.getAdmissionDate(), 
					student.getActiveDivision() != null ? student.getActiveDivision().getId() : null, 
					student.getActiveStudentDivision() != null ? student.getActiveStudentDivision().getRollNumber() : null))
				.collect(Collectors.toList());
		
		logger.info("Get all students API response: {}", response);
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<StudentResponse>>getSuccessResponse(response));
		
	}

}
