package com.assignmentevaluationportal.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

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
import com.assignmentevaluationportal.dto.response.CourseResponse;
import com.assignmentevaluationportal.dto.response.DivisionResponse;
import com.assignmentevaluationportal.dto.response.Response;
import com.assignmentevaluationportal.model.Course;
import com.assignmentevaluationportal.service.CourseService;

@RestController
@RequestMapping(ApiUrl.BASE_URL_V1)
public class CourseController {
	
	private final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	private CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@PostMapping(ApiUrl.COURSES)
	public ResponseEntity<?> addCourse(@Validated @RequestBody CourseRequest request) {
		logger.info("Create Course API request: {}", request);
		
		Course newCourse = courseService.createCourse(request.getName());
		CourseResponse newCourseResponse = new CourseResponse(newCourse.getId(), newCourse.getName());
		
		logger.info("Create Course API response: {}", newCourseResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.<CourseResponse>getSuccessResponse(newCourseResponse));
	}
	
	@GetMapping(ApiUrl.COURSES)
	public ResponseEntity<?> getAllCourses() {
		logger.info("Get all Courses API request");
		List<CourseResponse> courses = courseService.getAllCourses().stream()
				.map(course -> new CourseResponse(course.getId(), course.getName()))
				.collect(Collectors.toList());
		
		logger.info("Get all Courses API response: totalCourses: {}", courses.size());
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<CourseResponse>>getSuccessResponse(courses));
	}
	
	@GetMapping(ApiUrl.DIVISION_BY_COURSE_ID)
	public ResponseEntity<?> getDivisionsByCourse(@PathVariable @NotNull(message = "INVALID_COURSE_ID") Long courseId) { 
		logger.info("Get divisions by course API request: courseId: {}", courseId);
		
		List<DivisionResponse> divisions = courseService.getDivisionsByCourse(courseId).stream()
			.map(division -> new DivisionResponse(division.getId(), division.getName(), 
					division.getCapacity(), division.getStartYear(), division.getEndYear(), 
					division.getClassTeacher().getId(), division.getCourse().getId()))
			.collect(Collectors.toList());
		
		logger.info("Get divisions by course API response: {}", divisions);
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<DivisionResponse>>getSuccessResponse(divisions));
	}

}
