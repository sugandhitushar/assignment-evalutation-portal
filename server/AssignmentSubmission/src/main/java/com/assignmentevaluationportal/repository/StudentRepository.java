package com.assignmentevaluationportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.assignmentevaluationportal.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	@Query("SELECT s FROM Student s WHERE s.division.id = :divisionId")
	List<Student> findAllByDivision(Long divisionId);
	
	boolean existsByCollegeFileNumber(String collegeFileNumber);
	
	boolean existsByPermanentRegistrationNumber(String permanentRegistrationNumber);
}
