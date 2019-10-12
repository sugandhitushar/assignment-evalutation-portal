package com.assignmentevaluationportal.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentevaluationportal.constants.ApiUrl;
import com.assignmentevaluationportal.dto.request.TeacherRequest;
import com.assignmentevaluationportal.dto.response.Response;
import com.assignmentevaluationportal.dto.response.TeacherResponse;
import com.assignmentevaluationportal.model.Teacher;
import com.assignmentevaluationportal.service.TeacherService;

@RestController
@RequestMapping(ApiUrl.BASE_URL_V1)
public class TeacherController {
	
	private final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	
	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@PostMapping(ApiUrl.TEACHER_SIGNUP)
	public ResponseEntity<?> signup(@Validated @RequestBody TeacherRequest request) {
		logger.debug("Teacher Signup API request: {}", request);
		
		Teacher teacher = teacherService.signup(request.getFirstName(), 
				request.getLastName(), request.getEmail(), request.getPhoneNo(), 
				request.getPassword(), request.getAvatarUrl(), request.getGender(), request.getEmployeeId(), 
				request.getDesignation(), request.getJoiningDate());
		
		TeacherResponse response = new TeacherResponse(teacher.getId(), teacher.getUser().getFirstName(), 
				teacher.getUser().getLastName(), teacher.getUser().getEmail(), 
				teacher.getUser().getPhoneNo(), teacher.getUser().getAvatarUrl(), teacher.getUser().getGender(), 
				teacher.getUser().getStatus(), teacher.getEmployeeId(), teacher.getDesignation(), teacher.getJoiningDate());
		
		logger.debug("Teacher Signup API response: {}", response);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.<TeacherResponse>getSuccessResponse(response));		
	}
	
	@GetMapping(ApiUrl.TEACHERS)
	public ResponseEntity<?> getAllTeachers() {
		logger.debug("Get all teachers API ");
		
		List<TeacherResponse> teachers = teacherService.getAllTeachers().stream()
				.map(teacher -> new TeacherResponse(teacher.getId(), teacher.getUser().getFirstName(), 
						teacher.getUser().getLastName(), teacher.getUser().getEmail(), 
						teacher.getUser().getPhoneNo(), teacher.getUser().getAvatarUrl(), teacher.getUser().getGender(), 
						teacher.getUser().getStatus(), teacher.getEmployeeId(), teacher.getDesignation(), teacher.getJoiningDate()))
				.collect(Collectors.toList());
		
		logger.debug("Get all teachers API response: {}", teachers);
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<TeacherResponse>>getSuccessResponse(teachers));
	}
	
	@GetMapping(ApiUrl.TEACHER_BY_ID)
	public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
		logger.debug("Get teacher by id API:: id: {}", id);
		
		Teacher teacher = teacherService.getTeacherById(id);
		
		TeacherResponse response = null;
		if(teacher != null) {
			response = new TeacherResponse(teacher.getId(), teacher.getUser().getFirstName(), 
					teacher.getUser().getLastName(), teacher.getUser().getEmail(), 
					teacher.getUser().getPhoneNo(), teacher.getUser().getAvatarUrl(), teacher.getUser().getGender(), 
					teacher.getUser().getStatus(), teacher.getEmployeeId(), teacher.getDesignation(), teacher.getJoiningDate());
		}
		
		logger.debug("Get teacher by id API response: {}", response);
		return ResponseEntity.status(HttpStatus.OK).body(Response.<TeacherResponse>getSuccessResponse(response));
	}
	

}
