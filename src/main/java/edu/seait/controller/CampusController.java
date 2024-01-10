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

import edu.seait.dto.CampusDto;
import edu.seait.exception.InvalidCampusException;
import edu.seait.model.Campus;
import edu.seait.service.CampusService;

@CrossOrigin
@RestController
public class CampusController {

	@Autowired
	private CampusService campusService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/campuses", produces = "application/json")
	public List<CampusDto> getAllCampuses() {
		List<Campus> campuses = campusService.getAllCampuses();
		return campuses.stream().map(c -> convertToDto(c)).collect(Collectors.toList());
	}

	@GetMapping(value = "/campuses/{id}", produces = "application/json")
	public CampusDto getCampusById(@PathVariable Long id) {
		CampusDto campusDto = null;
		try {
			Campus campus = campusService.getCampus(id);
			campusDto = convertToDto(campus);
		} catch (InvalidCampusException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Campus ID", e);
		}
		return campusDto;
	}

	private CampusDto convertToDto(Campus campus) {
		CampusDto campusDto = modelMapper.map(campus, CampusDto.class);
		return campusDto;
	}

	private Campus convertToObject(CampusDto campusDto) {
		Campus campus = modelMapper.map(campusDto, Campus.class);
		return campus;
	}

}
