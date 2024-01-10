package edu.seait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.seait.exception.InvalidClassException;
import edu.seait.model.Class;
import edu.seait.service.ClassService;

@CrossOrigin
@RestController
public class ClassController {

	@Autowired
	private ClassService classService;

	@GetMapping(value = "/classes")
	public List<Class> getAllClasses() {
		return classService.getAllClasses();
	}

	@GetMapping(value = "/classes/{id}")
	public Class getClassById(@PathVariable Long id) {
		Class theClass = null;
		try {
			theClass = classService.getClassById(id);
		} catch (InvalidClassException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Class ID", e);
		}
		return theClass;
	}

}
