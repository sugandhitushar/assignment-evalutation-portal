package com.assignmentevaluationportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignmentevaluationportal.model.Division;

public interface DivisionRepository extends JpaRepository<Division, Long> {
	
	@Query("SELECT d from  Division d WHERE d.course.id = :courseId AND d.status = 1")
	List<Division> findAllByCourse(Long courseId);

}
