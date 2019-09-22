package com.assignmentevaluationportal.serviceImpl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.Gender;
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
			String avatarUrl, Gender gender, String employeeId, String designation, LocalDate joiningDate) {

		Teacher teacher = new Teacher(firstName, lastName, email, phoneNo, PasswordUtil.encrypt(password), avatarUrl, 
				gender, employeeId, designation, joiningDate, null);
		
		teacherRepository.saveAndFlush(teacher);
		return teacher;
	}

}
