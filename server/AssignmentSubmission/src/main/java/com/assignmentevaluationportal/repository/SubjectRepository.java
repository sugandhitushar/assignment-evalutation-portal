package com.assignmentevaluationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignmentevaluationportal.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, String> {

}
