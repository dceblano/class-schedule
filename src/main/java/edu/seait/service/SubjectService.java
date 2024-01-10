package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidSubjectException;
import edu.seait.model.Subject;

public interface SubjectService {

	List<Subject> getAllSubjects();

	Subject getSubjectById(Long id) throws InvalidSubjectException;

}