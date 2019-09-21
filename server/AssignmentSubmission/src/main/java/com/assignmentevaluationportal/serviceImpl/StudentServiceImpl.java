package com.assignmentevaluationportal.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.Student;
import com.assignmentevaluationportal.repository.DivisionRepository;
import com.assignmentevaluationportal.repository.StudentRepository;
import com.assignmentevaluationportal.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private DivisionRepository divisionRepository;
	private StudentRepository studentRepository;
	
	public StudentServiceImpl(DivisionRepository divisionRepository, StudentRepository studentRepository) {
		super();
		this.divisionRepository = divisionRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public Student signup(String firstName, String lastName, String email, String phoneNo, String password,
			String avatarUrl, Gender gender, String collegeFileNumber, String permanentRegistrationNumber,
			LocalDate admissionDate, Long divisionId) {
		
		Optional<Division> division = divisionRepository.findById(divisionId);
		
		if(division.isPresent()) {
			Student student = new Student(firstName, lastName, email, phoneNo, password, avatarUrl, 
					gender, collegeFileNumber, permanentRegistrationNumber, admissionDate, null, division.get());
			
			return studentRepository.saveAndFlush(student);
			
		} else {
			return null;
			// TODO: Define custom error message for division not found
		}
	}

}
