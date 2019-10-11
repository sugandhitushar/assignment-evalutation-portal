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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentevaluationportal.constants.ApiUrl;
import com.assignmentevaluationportal.dto.SubjectDTO;
import com.assignmentevaluationportal.dto.request.DivisionRequest;
import com.assignmentevaluationportal.dto.request.DivisionSubjectTeacherRequest;
import com.assignmentevaluationportal.dto.response.DivisionResponse;
import com.assignmentevaluationportal.dto.response.Response;
import com.assignmentevaluationportal.dto.response.StudentResponse;
import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.Subject;
import com.assignmentevaluationportal.service.DivisionService;
import com.assignmentevaluationportal.service.StudentService;
import com.assignmentevaluationportal.service.SubjectService;

@RestController
@RequestMapping(ApiUrl.BASE_URL_V1)
public class AdminController {
	
	private final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	private SubjectService subjectService;
	private DivisionService divisionService;
	private StudentService studentService;

	public AdminController(SubjectService subjectService, DivisionService divisionService,
			StudentService studentService) {
		this.subjectService = subjectService;
		this.divisionService = divisionService;
		this.studentService = studentService;
	}

	@PostMapping(ApiUrl.DIVISIONS)
	public ResponseEntity<?> createDivision(@Validated @RequestBody DivisionRequest request) {
		logger.info("Create Division API request: {}", request);
		 Division newDivision = divisionService.createDivision(request.getName(), request.getCapacity(), request.getStartYear(), 
				 request.getEndYear(), request.getClassTeacherId(), request.getCourseId());
		 
		 DivisionResponse response = new DivisionResponse(newDivision.getId(), newDivision.getName(), 
				 newDivision.getCapacity(), newDivision.getStartYear(), newDivision.getEndYear(), 
				 newDivision.getClassTeacher().getId(), newDivision.getCourse().getId());
		
		 logger.info("Create Division API response: {}", response);
		 return ResponseEntity.status(HttpStatus.CREATED).body(Response.<DivisionResponse>getSuccessResponse(response));
	}
	
	@GetMapping(ApiUrl.DIVISIONS)
	public ResponseEntity<?> getAllDivisions() {
		logger.info("Get all Divisions API request");
		List<DivisionResponse> divisions = divisionService.getAllDivisions().stream()
				.map(division -> new DivisionResponse(division.getId(), division.getName(), 
						division.getCapacity(), division.getStartYear(), division.getEndYear(), 
						division.getClassTeacher().getId(), division.getCourse().getId()))
				.collect(Collectors.toList());
		
		logger.info("Get all Divisions API response: totalCourses: {}", divisions.size());
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<DivisionResponse>>getSuccessResponse(divisions));
	}
	
	@GetMapping(ApiUrl.ALL_STUDENTS_OF_DIVISION)
	public ResponseEntity<?> getStudentsByDivision(@PathVariable @NotNull(message = "INVALID_DIVISION_ID") Long divisionId) {
		logger.info("Get students by Division API request: divisionId: {}", divisionId);
		
		List<StudentResponse> students = studentService.getStudentsByDivision(divisionId).stream()
			.map(s -> new StudentResponse(s.getId(), s.getUser().getFirstName(), 
					s.getUser().getLastName(), s.getUser().getEmail(), s.getUser().getPhoneNo(), 
					s.getUser().getAvatarUrl(), s.getUser().getGender(), s.getCollegeFileNumber(), 
					s.getPermanentRegistrationNumber(), s.getAdmissionDate(), 
					s.getActiveDivision() != null ? s.getActiveDivision().getId() : null, 
					s.getActiveStudentDivision() != null ? s.getActiveStudentDivision().getRollNumber() : null))
			.collect(Collectors.toList());
		
		logger.info("Get students by Division API response: Students: {}", students);
		return ResponseEntity.status(HttpStatus.OK).body(Response.<List<StudentResponse>>getSuccessResponse(students));
	}
	
	@PostMapping(ApiUrl.SUBJECTS)
	public ResponseEntity<?> createSubject(@Validated @RequestBody SubjectDTO request) { 
		logger.info("Create Subject API request: {}", request);
		
		Subject subject = subjectService.createSubject(request.getId(), request.getName());
		SubjectDTO response = new SubjectDTO(subject.getId(), subject.getName());
		
		logger.info("Create Subject API response: {}", response);
		return ResponseEntity.status(HttpStatus.CREATED).body(Response.<SubjectDTO>getSuccessResponse(response));
	}
	
	@PutMapping(ApiUrl.DIVISION_SUBJECTS)
	public ResponseEntity<?> assignDivisionSubjectTeacher(
			@PathVariable @NotNull(message = "INVALID_DIVISION_ID") Long divisionId,
			@Validated @RequestBody DivisionSubjectTeacherRequest request) {
		logger.info("Assign Subject Teacher to Division API request: {}", request);
		
		divisionService.assignSubjectTeacherToDivision(divisionId, request.getSubjectId(), request.getTeacherId());
		
		logger.info("Assign Subject Teacher to Division API response: OK");
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
