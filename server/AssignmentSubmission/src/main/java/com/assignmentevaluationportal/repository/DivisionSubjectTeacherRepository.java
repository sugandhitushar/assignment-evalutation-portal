package com.assignmentevaluationportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.DivisionSubjectTeacher;
import com.assignmentevaluationportal.model.Subject;
import com.assignmentevaluationportal.model.Teacher;

public interface DivisionSubjectTeacherRepository extends JpaRepository<DivisionSubjectTeacher, Long>{

	DivisionSubjectTeacher findByDivisionAndSubjectAndTeacher(Division division, Subject subject, Teacher teacher);
}
