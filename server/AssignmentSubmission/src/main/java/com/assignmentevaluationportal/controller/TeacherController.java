package com.assignmentevaluationportal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	@PostMapping(ApiUrl.TEACHER_SIGNUP_ENDPOINT)
	public ResponseEntity<?> signup(@Validated @RequestBody TeacherRequest request) {
		logger.debug("Teacher Signup API request: ", request);
		
		Teacher teacher = teacherService.signup(request.getFirstName(), 
				request.getLastName(), request.getEmail(), request.getPhoneNo(), 
				request.getPassword(), request.getAvatarUrl(), request.getGender(), request.getEmployeeId(), 
				request.getDesignation(), request.getJoiningDate());
		
		TeacherResponse response = new TeacherResponse(teacher.getId(), teacher.getUser().getFirstName(), 
				teacher.getUser().getLastName(), teacher.getUser().getEmail(), 
				teacher.getUser().getPhoneNo(), teacher.getUser().getAvatarUrl(), teacher.getUser().getGender(), 
				teacher.getEmployeeId(), teacher.getDesignation(), teacher.getJoiningDate());
		
		logger.debug("Teacher Signup API response: ", response);
		return ResponseEntity.<TeacherResponse>ok(response);
	}
	
	@GetMapping(ApiUrl.TEACHER_ENDPOINT)
	public ResponseEntity<?> getAllTeachers(){
		logger.debug("Get Teachers API");
		
		return ResponseEntity.<TeacherResponse>ok(null);
	}
	
	
	@GetMapping(ApiUrl.GET_TEACHER_BY_ID)
	public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
		
		ResponseEntity<?> responseEntity = null;
		Teacher teacher = teacherService.getTeacher(id);
		if(teacher != null) {
			TeacherResponse teacherResponse = new TeacherResponse(teacher.getId(), teacher.getUser().getFirstName(), 
					teacher.getUser().getLastName(), teacher.getUser().getEmail(), teacher.getUser().getPhoneNo(), 
					teacher.getUser().getAvatarUrl(), teacher.getUser().getGender(), null, null, null);
			responseEntity = ResponseEntity.ok(teacherResponse);
		}else {
			responseEntity = ResponseEntity.notFound().build();
		}
		return responseEntity;
	}
	
}
	