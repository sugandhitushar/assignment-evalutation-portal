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
import com.assignmentevaluationportal.dto.request.CourseRequest;
import com.assignmentevaluationportal.dto.request.DivisionRequest;
import com.assignmentevaluationportal.dto.response.CourseResponse;
import com.assignmentevaluationportal.dto.response.DivisionResponse;
import com.assignmentevaluationportal.dto.response.Response;
import com.assignmentevaluationportal.model.Course;
import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.service.CourseService;

@RestController
@RequestMapping(ApiUrl.BASE_URL_V1)
public class AdminController {
	
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private CourseService courseService;
	
	public AdminController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@PostMapping(ApiUrl.COURSES)
	public ResponseEntity<?> addCourse(@Validated @RequestBody CourseRequest request) {
		logger.debug("Create Course API request: {}", request);
		
		Course newCourse = courseService.createCourse(request.getName());
		CourseResponse newCourseResponse = new CourseResponse(newCourse.getId(), newCourse.getName());
		
		logger.debug("Create Course API response: {}", newCourseResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.<CourseResponse>getSuccessResponse(newCourseResponse));
	}
	
	@GetMapping(ApiUrl.COURSES)
	public ResponseEntity<?> getAllCourses() {
		logger.debug("Get all Courses API request");
		List<CourseResponse> courses = courseService.getAllCourses().stream()
				.map(course -> new CourseResponse(course.getId(), course.getName()))
				.collect(Collectors.toList());
		
		logger.debug("Get all Courses API response: totalCourses: {}", courses.size());
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<CourseResponse>>getSuccessResponse(courses));
	}
	
	@PostMapping(ApiUrl.DIVISIONS)
	public ResponseEntity<?> createDivision(@Validated @RequestBody DivisionRequest request) {
		logger.debug("Create Division API request: {}", request);
		 Division newDivision = courseService.createDivision(request.getName(), request.getCapacity(), request.getStartYear(), 
				 request.getEndYear(), request.getClassTeacherId(), request.getCourseId());
		 
		 DivisionResponse response = new DivisionResponse(newDivision.getId(), newDivision.getName(), 
				 newDivision.getCapacity(), newDivision.getStartYear(), newDivision.getEndYear(), 
				 newDivision.getClassTeacher().getId(), newDivision.getCourse().getId());
		
		 logger.debug("Create Division API response: {}", response);
		 return ResponseEntity.status(HttpStatus.CREATED).body(Response.<DivisionResponse>getSuccessResponse(response));
	}
	
	@GetMapping(ApiUrl.DIVISIONS)
	public ResponseEntity<?> getAllDivisions() {
		logger.debug("Get all Divisions API request");
		List<DivisionResponse> divisions = courseService.getAllDivisions().stream()
				.map(division -> new DivisionResponse(division.getId(), division.getName(), 
						division.getCapacity(), division.getStartYear(), division.getEndYear(), 
						division.getClassTeacher().getId(), division.getCourse().getId()))
				.collect(Collectors.toList());
		
		logger.debug("Get all Divisions API response: totalCourses: {}", divisions.size());
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<DivisionResponse>>getSuccessResponse(divisions));
	}
	
	@GetMapping(ApiUrl.GET_DIVISION_BY_COURSE_ID)
	public ResponseEntity<?> getDivisionsByCourse(@PathVariable Long courseId) { 
		logger.debug("Get divisions by course API request: courseId: {}", courseId);
		
		List<DivisionResponse> divisions = courseService.getDivisionsByCourse(courseId).stream()
			.map(division -> new DivisionResponse(division.getId(), division.getName(), 
					division.getCapacity(), division.getStartYear(), division.getEndYear(), 
					division.getClassTeacher().getId(), division.getCourse().getId()))
			.collect(Collectors.toList());
		
		logger.debug("Get divisions by course API response: {}", divisions);
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<DivisionResponse>>getSuccessResponse(divisions));
	}
	
	
}
