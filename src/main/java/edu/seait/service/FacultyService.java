package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidFacultyException;
import edu.seait.model.Faculty;

public interface FacultyService {

	List<Faculty> getAllFaculties();

	List<Faculty> getFacultiesByCampus(Long campusId);

	Faculty getFacultyById(Long id) throws InvalidFacultyException;

}