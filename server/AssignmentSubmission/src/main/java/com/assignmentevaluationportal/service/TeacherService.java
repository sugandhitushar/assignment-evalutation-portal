package com.assignmentevaluationportal.service;

import java.util.List;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.model.Teacher;

public interface TeacherService {
	
	/**
	 * Service for signing up a teacher
	 * @param firstName First name of teacher
	 * @param lastName Last name of teacher
	 * @param email  Valid email id
	 * @param phoneNo A valid phone number without any spaces or dashes
	 * @param password Password of the teacher
	 * @param avatarUrl URL for teacher's avatar / profile picture.
	 * @param gender Teacher's gender
	 * @param employeeId Teacher's employee id for college
	 * @param designation Teacher's designation
	 * @param joiningDate Teacher's joining date in college.
	 * @return Teacher Newly created teacher
	 * */
	public Teacher signup(String firstName, String lastName, String email, String phoneNo, 
			String password, String avatarUrl, Gender gender, String employeeId,
			String designation, Long joiningDate);

	/**
	 * Service to get all teachers
	 * @return List<Teacher> All teachers
	 * */
	public List<Teacher> getAllTeachers();
	
	/**
	 * Get Teacher by Id
	 * @param id Id of teacher
	 * @return Teacher teacher corresponding to the id
	 * */
	public Teacher getTeacherById(Long id);


}
