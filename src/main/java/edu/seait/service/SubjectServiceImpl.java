package edu.seait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidSubjectException;
import edu.seait.model.Subject;
import edu.seait.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	@Override
	public Subject getSubjectById(Long id) throws InvalidSubjectException {
		Optional<Subject> subjects = subjectRepository.findById(id);
		if (subjects.isPresent()) {
			return subjects.get();
		} else {
			throw new InvalidSubjectException("Subject you are looking for does not exist!");
		}
	}

}
