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

import edu.seait.dto.SubjectDto;
import edu.seait.exception.InvalidSubjectException;
import edu.seait.model.Subject;
import edu.seait.service.SubjectService;

@CrossOrigin
@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/subjects")
	public List<SubjectDto> getAllSubjects() {
		List<Subject> subjects = subjectService.getAllSubjects();
		List<SubjectDto> subjectsDto = subjects.stream().map(s -> convertToDto(s)).toList();
		return subjectsDto;
	}

	@GetMapping(value = "/subjects/{id}")
	public SubjectDto getSubjectDtoById(@PathVariable Long id) {
		SubjectDto subjectDto = null;
		try {
			Subject subject = subjectService.getSubjectById(id);
			subjectDto = convertToDto(subject);
		} catch (InvalidSubjectException e) {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Provide valid Subject ID", e);
		}
		return subjectDto;
	}

	private SubjectDto convertToDto(Subject subject) {
		SubjectDto subjectDto = modelMapper.map(subject, SubjectDto.class);
		return subjectDto;
	}

	private Subject convertToObject(SubjectDto subjectDto) {
		Subject subject = modelMapper.map(subjectDto, Subject.class);
		return subject;
	}

}
