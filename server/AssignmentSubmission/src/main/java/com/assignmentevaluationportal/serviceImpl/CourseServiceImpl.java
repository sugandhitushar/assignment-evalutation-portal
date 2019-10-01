package com.assignmentevaluationportal.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.exception.AEPException;
import com.assignmentevaluationportal.model.Course;
import com.assignmentevaluationportal.model.Division;
import com.assignmentevaluationportal.model.Teacher;
import com.assignmentevaluationportal.repository.CourseRepository;
import com.assignmentevaluationportal.repository.DivisionRepository;
import com.assignmentevaluationportal.repository.TeacherRepository;
import com.assignmentevaluationportal.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	CourseRepository courseRepository;
	TeacherRepository teacherRepository;
	DivisionRepository divisionRepository;

	public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, DivisionRepository divisionRepository) {
		this.courseRepository = courseRepository;
		this.teacherRepository = teacherRepository;
		this.divisionRepository = divisionRepository;
	}

	@Override
	public Course createCourse(String name) {
		
		Course existingCourse = courseRepository.findByName(name);
		
		if(existingCourse != null) {
			throw new AEPException(AEPError.COURSE_ALREADY_EXISTS);
		}
		
		Course course = new Course(name);
		courseRepository.saveAndFlush(course);
		return course;
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
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
	public List<Division> getDivisionsByCourse(Long courseId) {
		Optional<Course> course = courseRepository.findById(courseId);
		
		if(!course.isPresent()) {
			throw new AEPException(AEPError.COURSE_NOT_FOUND);
		}
		
		return divisionRepository.findAllByCourse(courseId);
	}
	
	

}
