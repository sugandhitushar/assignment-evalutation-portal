package com.assignmentevaluationportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignmentevaluationportal.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query("SELECT s FROM Student s WHERE s.id IN (SELECT sd.student.id FROM StudentDivision sd WHERE sd.division.id = :divisionId AND sd.status = 1)")
	List<Student> findAllByDivision(Long divisionId);
	
	boolean existsByCollegeFileNumber(String collegeFileNumber);
	
	boolean existsByPermanentRegistrationNumber(String permanentRegistrationNumber);
}
