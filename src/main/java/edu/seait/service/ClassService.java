package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidClassException;
import edu.seait.model.Class;

public interface ClassService {

	List<Class> getAllClasses();

	Class getClassById(Long id) throws InvalidClassException;

}