package com.assignmentevaluationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignmentevaluationportal.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
