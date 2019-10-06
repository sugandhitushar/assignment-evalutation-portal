package com.assignmentevaluationportal.service;

import java.util.List;

import com.assignmentevaluationportal.model.Course;
import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.Student;

public interface CourseService {
	
	/**
	 * Service creating a course / class.
	 * @param name Name of the course
	 * @return Course Newly created course/class
	 * */
	public Course createCourse(String name);
	
	/**
	 * Get all courses/classes
	 * @return Courses List of all courses
	 * */
	public List<Course> getAllCourses();
	
	/**
	 * Service for creating a division.
	 * @param name Name of the division
	 * @param capacity Capacity of this division
	 * @param startYear Division starting year
	 * @param endYear Division ending year
	 * @param classTeacherId Id of class teacher
	 * @param courseId Id of the course/class
	 * @return Division Newly created division.
	 * */
	public Division createDivision(String name, Integer capacity, Integer startYear, 
			Integer endYear, Long classTeacherId, Long courseId);
	
	/**
	 * Get all divisions
	 * @return List<Division> List of all divisions
	 * */
	public List<Division> getAllDivisions();
	
	/**
	 * Get divisions by course
	 * @param courseId Course id
	 * @return List<Division> List of all divisions
	 * */
	public List<Division> getDivisionsByCourse(Long courseId);
	
	/**
	 * Get students by division
	 * @param divisionId Division id
	 * @return List<Student> List of all students
	 * */
	public List<Student> getStudentsByDivision(Long divisionId);
}
