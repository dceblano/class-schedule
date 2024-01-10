package edu.seait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidFacultyException;
import edu.seait.model.Faculty;
import edu.seait.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public List<Faculty> getAllFaculties() {
		return facultyRepository.findAll();
	}

	public List<Faculty> getFacultiesByCampus(Long campusId) {
		return facultyRepository.findFacultyByCampusId(campusId);
	}

	@Override
	public Faculty getFacultyById(Long id) throws InvalidFacultyException {
		Optional<Faculty> faculties = facultyRepository.findById(id);
		if (faculties.isPresent()) {
			return faculties.get();
		} else {
			throw new InvalidFacultyException("Faculty you are looking for does not exist!");
		}
	}

}
