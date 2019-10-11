package com.assignmentevaluationportal.service;

import java.util.List;

import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.DivisionSubjectTeacher;

public interface DivisionService {
	
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
	 * Assign subject teacher to Division
	 * @param divisionId Division id
	 * @param subjectId Subject id
	 * @param teacherId Teacher id
	 * @return DivisionSubjectTeacher
	 * */
	public DivisionSubjectTeacher assignSubjectTeacherToDivision(Long divisionId, String subjectId, Long teacherId);

}
