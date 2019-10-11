package com.assignmentevaluationportal.service;

import java.util.List;

import com.assignmentevaluationportal.model.Course;
import com.assignmentevaluationportal.model.Division;

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
	 * Get divisions by course
	 * @param courseId Course id
	 * @return List<Division> List of all divisions
	 * */
	public List<Division> getDivisionsByCourse(Long courseId);
}
