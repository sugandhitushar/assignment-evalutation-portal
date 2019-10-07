package com.assignmentevaluationportal.serviceImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.exception.AEPException;
import com.assignmentevaluationportal.model.Teacher;
import com.assignmentevaluationportal.repository.TeacherRepository;
import com.assignmentevaluationportal.service.TeacherService;
import com.assignmentevaluationportal.util.PasswordUtil;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	private TeacherRepository teacherRepository;
	
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@Override
	public Teacher signup(String firstName, String lastName, String email, String phoneNo, String password,
			String avatarUrl, Gender gender, String employeeId, String designation, Long joiningDate) {
		
		LocalDate joiningLocalDate = Instant.ofEpochMilli(joiningDate).atZone(ZoneId.systemDefault()).toLocalDate(); 
		
		if(joiningLocalDate.isAfter(LocalDate.now())) {
			throw new AEPException(AEPError.INVALID_JOINING_DATE);
		}

		Teacher teacher = new Teacher(firstName, lastName, email, phoneNo, PasswordUtil.encrypt(password), avatarUrl, 
				gender, employeeId, designation, joiningLocalDate, null);
		
		teacherRepository.saveAndFlush(teacher);
		return teacher;
	}

	@Override
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher getTeacherById(Long id) {
		return teacherRepository.findById(id).orElseGet(null);
	}

}
