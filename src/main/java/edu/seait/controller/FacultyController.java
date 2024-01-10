package edu.seait.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.seait.dto.FacultyDto;
import edu.seait.exception.InvalidFacultyException;
import edu.seait.model.Faculty;
import edu.seait.service.FacultyService;

@CrossOrigin
@RestController
public class FacultyController {

	@Autowired
	private FacultyService facultyService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/faculties")
	public List<Faculty> getAllFaculties() {
		return facultyService.getAllFaculties();
	}

	@GetMapping(value = "/faculties/{id}")
	public Faculty getFacultyById(@PathVariable Long id) {
		Faculty faculty = null;

		try {
			faculty = facultyService.getFacultyById(id);
		} catch (InvalidFacultyException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Faculty ID", e);
		}

		return faculty;
	}

	@GetMapping(value = "/campuses/{id}/faculties")
	public List<FacultyDto> getFacultiesByCampus(@PathVariable Long id) {
		List<Faculty> faculties = facultyService.getFacultiesByCampus(id);
		List<FacultyDto> facultyDtos = faculties.stream().map(r -> convertToDto(r)).collect(Collectors.toList());
		return facultyDtos;
	}

	private FacultyDto convertToDto(Faculty faculty) {
		FacultyDto facultyDto = modelMapper.map(faculty, FacultyDto.class);
		return facultyDto;
	}

}
