package edu.seait.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.seait.dto.SchoolYearDto;
import edu.seait.exception.InvalidSchoolYearException;
import edu.seait.model.SchoolYear;
import edu.seait.service.SchoolYearService;

@CrossOrigin
@RestController
public class SchoolYearController {

	@Autowired
	private SchoolYearService schoolYearService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/schoolyears")
	public List<SchoolYearDto> getAllSchoolYears() {
		List<SchoolYear> schoolYears = schoolYearService.getAllSchoolyears();
		List<SchoolYearDto> schoolYearsDto = schoolYears.stream().map(s -> convertToDto(s)).toList();
		return schoolYearsDto;
	}

	@GetMapping(value = "/schoolyears/{id}")
	public SchoolYearDto getSchoolYearById(@PathVariable Long id) {
		SchoolYearDto schoolYearDto = null;

		try {
			SchoolYear schoolyear = schoolYearService.getSchoolYearById(id);
			schoolYearDto = convertToDto(schoolyear);
		} catch (InvalidSchoolYearException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please provide a valid School Year ID", e);
		}

		return schoolYearDto;
	}

	private SchoolYearDto convertToDto(SchoolYear schoolYear) {
		SchoolYearDto schoolYearDto = modelMapper.map(schoolYear, SchoolYearDto.class);
		return schoolYearDto;
	}

	private SchoolYear convertToObjject(SchoolYearDto schoolYearDto) {
		SchoolYear schoolYear = modelMapper.map(schoolYearDto, SchoolYear.class);
		return schoolYear;
	}

}
