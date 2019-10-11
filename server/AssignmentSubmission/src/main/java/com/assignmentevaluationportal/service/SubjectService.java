package com.assignmentevaluationportal.service;

import com.assignmentevaluationportal.model.Subject;

public interface SubjectService {
	
	/**
	 * Create a new subject
	 * @param id Subject id
	 * @param name Subject name
	 * @return Subject Newly created subject
	 * */
	public Subject createSubject(String id, String name);
}
