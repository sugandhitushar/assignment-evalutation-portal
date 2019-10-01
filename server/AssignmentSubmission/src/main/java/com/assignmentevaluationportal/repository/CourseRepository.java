package com.assignmentevaluationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignmentevaluationportal.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	Course findByName(String name);
}
