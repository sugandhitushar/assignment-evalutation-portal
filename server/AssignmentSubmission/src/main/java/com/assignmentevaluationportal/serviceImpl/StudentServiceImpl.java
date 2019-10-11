package com.assignmentevaluationportal.serviceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.exception.AEPException;
import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.Student;
import com.assignmentevaluationportal.model.StudentDivision;
import com.assignmentevaluationportal.repository.DivisionRepository;
import com.assignmentevaluationportal.repository.StudentDivisionRepository;
import com.assignmentevaluationportal.repository.StudentRepository;
import com.assignmentevaluationportal.service.StudentService;
import com.assignmentevaluationportal.util.PasswordUtil;

@Service
public class StudentServiceImpl implements StudentService {

	private DivisionRepository divisionRepository;
	private StudentRepository studentRepository;
	private StudentDivisionRepository studentDivisionRepository;

	public StudentServiceImpl(DivisionRepository divisionRepository, StudentRepository studentRepository,
			StudentDivisionRepository studentDivisionRepository) {
		this.divisionRepository = divisionRepository;
		this.studentRepository = studentRepository;
		this.studentDivisionRepository = studentDivisionRepository;
	}

	@Override
	public Student signup(String firstName, String lastName, String email, String phoneNo, String password,
			String avatarUrl, Gender gender, String collegeFileNumber, String permanentRegistrationNumber,
			Long admissionDate, Long divisionId, Integer rollNumber) {
		
		LocalDate admissionLocalDate = null;
		if(admissionDate != null) {
			admissionLocalDate = Instant.ofEpochMilli(admissionDate).atZone(ZoneId.systemDefault()).toLocalDate();
			if(admissionLocalDate.isAfter(LocalDate.now())) {
				throw new AEPException(AEPError.INVALID_ADMISSION_DATE);
			}
		}
		
		if(studentRepository.existsByCollegeFileNumber(collegeFileNumber)) {
			throw new AEPException(AEPError.STUDENT_WITH_COLLEGE_FILE_NUMBER_ALREADY_EXISTS);
		}
		
		if(studentRepository.existsByPermanentRegistrationNumber(permanentRegistrationNumber)) {
			throw new AEPException(AEPError.STUDENT_WITH_PERMANENT_REGISTRATION_NUMBER_ALREADY_EXISTS);
		}
		
		Optional<Division> division = divisionRepository.findById(divisionId);
		
		if(!division.isPresent()) {
			throw new AEPException(AEPError.DIVISION_NOT_FOUND);
		}
		
		Student student = new Student(firstName, lastName, email, phoneNo, PasswordUtil.encrypt(password), avatarUrl, 
				gender, collegeFileNumber, permanentRegistrationNumber, admissionLocalDate, null);
		
		student = studentRepository.saveAndFlush(student);
		
		StudentDivision studentDivision = new StudentDivision(student, division.get(), rollNumber);
		studentDivisionRepository.saveAndFlush(studentDivision);
		
		student.addDivision(studentDivision);
		
		return student;		
	}
	
	@Override
	public List<Student> getStudentsByDivision(Long divisionId) {
		return studentRepository.findAllByDivision(divisionId);
	}

}
