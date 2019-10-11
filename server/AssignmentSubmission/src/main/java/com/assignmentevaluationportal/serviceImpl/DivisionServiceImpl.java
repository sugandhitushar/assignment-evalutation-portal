package com.assignmentevaluationportal.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.constants.Status;
import com.assignmentevaluationportal.exception.AEPException;
import com.assignmentevaluationportal.model.Course;
import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.DivisionSubjectTeacher;
import com.assignmentevaluationportal.model.Subject;
import com.assignmentevaluationportal.model.Teacher;
import com.assignmentevaluationportal.repository.CourseRepository;
import com.assignmentevaluationportal.repository.DivisionRepository;
import com.assignmentevaluationportal.repository.DivisionSubjectTeacherRepository;
import com.assignmentevaluationportal.repository.SubjectRepository;
import com.assignmentevaluationportal.repository.TeacherRepository;
import com.assignmentevaluationportal.service.DivisionService;

@Service
public class DivisionServiceImpl implements DivisionService {
	
	TeacherRepository teacherRepository;
	DivisionRepository divisionRepository;
	CourseRepository courseRepository;
	SubjectRepository subjectRepository;
	DivisionSubjectTeacherRepository divisionSubjectTeacherRepository;

	public DivisionServiceImpl(TeacherRepository teacherRepository,
			DivisionRepository divisionRepository, CourseRepository courseRepository,
			SubjectRepository subjectRepository, DivisionSubjectTeacherRepository divisionSubjectTeacherRepository) {
		this.teacherRepository = teacherRepository;
		this.divisionRepository = divisionRepository;
		this.courseRepository = courseRepository;
		this.subjectRepository = subjectRepository;
		this.divisionSubjectTeacherRepository = divisionSubjectTeacherRepository;
	}

	@Override
	public Division createDivision(String name, Integer capacity, Integer startYear, Integer endYear,
			Long classTeacherId, Long courseId) {
		
		Optional<Teacher> classTeacher = teacherRepository.findById(classTeacherId);
		Optional<Course> course = courseRepository.findById(courseId);
		
		if(!classTeacher.isPresent()) {
			throw new AEPException(AEPError.TEACHER_NOT_FOUND);
		}
		
		if(!course.isPresent()) {
			throw new AEPException(AEPError.COURSE_NOT_FOUND);
		}
		
		Division division = new Division(name, capacity, startYear, endYear, classTeacher.get(), course.get());
		division = divisionRepository.saveAndFlush(division);
		
		return division;
	}

	@Override
	public List<Division> getAllDivisions() {
		return divisionRepository.findAll();
	}

	@Override
	public DivisionSubjectTeacher assignSubjectTeacherToDivision(Long divisionId, String subjectId, Long teacherId) {
		
		Optional<Division> division = divisionRepository.findById(divisionId);
		
		if(!division.isPresent()) {
			throw new AEPException(AEPError.DIVISION_NOT_FOUND);
		}
		
		Optional<Subject> subject = subjectRepository.findById(subjectId);
		
		if(!subject.isPresent()) {
			throw new AEPException(AEPError.SUBJECT_NOT_FOUND);
		}
		
		Optional<Teacher> teacher = teacherRepository.findById(teacherId);
		
		if(!teacher.isPresent()) {
			throw new AEPException(AEPError.TEACHER_NOT_FOUND);
		}
		
		DivisionSubjectTeacher divisionSubjectTeacher = divisionSubjectTeacherRepository.findByDivisionAndSubjectAndTeacher(division.get(), 
				subject.get(), teacher.get());
		
		if(divisionSubjectTeacher != null) {
			if(divisionSubjectTeacher.getStatus() == Status.INACTIVE) {
				divisionSubjectTeacher.setStatus(Status.ACTIVE);
				divisionSubjectTeacher = divisionSubjectTeacherRepository.saveAndFlush(divisionSubjectTeacher);
			}
			return divisionSubjectTeacher;
		}
		
		divisionSubjectTeacher = new DivisionSubjectTeacher(division.get(), subject.get(), teacher.get());
		
		return divisionSubjectTeacherRepository.saveAndFlush(divisionSubjectTeacher);
	}

}
