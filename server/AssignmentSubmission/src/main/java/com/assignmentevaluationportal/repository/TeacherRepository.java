package com.assignmentevaluationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignmentevaluationportal.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
