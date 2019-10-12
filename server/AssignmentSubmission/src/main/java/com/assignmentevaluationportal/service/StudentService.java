package com.assignmentevaluationportal.service;

import java.util.List;

import com.assignmentevaluationportal.constants.Gender;
import com.assignmentevaluationportal.model.Student;

public interface StudentService {

	/**
	 * Service for signing up a user
	 * @param firstName First name of user
	 * @param lastName Last name of user
	 * @param email  Valid email id
	 * @param phoneNo A valid phone number without any spaces or dashes
	 * @param password Password of the user
	 * @param avatarUrl URL for user's avatar / profile picture.
	 * @param gender user's gender
	 * @param collegeFileNumber Student's unique file number registered with the college.
	 * @param permanentRegistrationNumber A unique identification number registered with University. 
	 * @param admissionDate Student's admission date in college.
	 * @param divisionId Student's division id
	 * @return Student Newly created student
	 * */
	public Student signup(String firstName, String lastName, String email, String phoneNo, 
			String password, String avatarUrl, Gender gender, String collegeFileNumber,
			String permanentRegistrationNumber, Long admissionDate, Long divisionId, Integer rollNumber);
	
	/**
	 * Get students by division
	 * @param divisionId Division id
	 * @return List<Student> List of all students
	 * */
	public List<Student> getStudentsByDivision(Long divisionId);
	
	/**
	 * Get all students
	 * @return List<Student> List of all students
	 * */
	public List<Student> getAllStudents();

}
