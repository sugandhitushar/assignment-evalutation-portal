package com.assignmentevaluationportal.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignmentevaluationportal.constants.AEPError;
import com.assignmentevaluationportal.exception.AEPException;
import com.assignmentevaluationportal.model.Subject;
import com.assignmentevaluationportal.repository.SubjectRepository;
import com.assignmentevaluationportal.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	private SubjectRepository subjectRepository;

	public SubjectServiceImpl(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	@Override
	public Subject createSubject(String id, String name) {
		
		Optional<Subject> existingSubject = subjectRepository.findById(id);
		
		if(existingSubject.isPresent()) {
			throw new AEPException(AEPError.SUBJECT_WITH_ID_ALREADY_EXISTS);
		}
		
		Subject subject = new Subject(id, name);
		
		subject = subjectRepository.save(subject);
		return subject;
	}
}
