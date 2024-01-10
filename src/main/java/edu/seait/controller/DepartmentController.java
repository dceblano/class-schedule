package edu.seait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.seait.exception.InvalidDepartmentException;
import edu.seait.model.Department;
import edu.seait.service.DepartmentService;

@CrossOrigin
@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping(value = "/departments")
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartment();
	}
	
	@GetMapping(value = "/departments/{id}")
	public Department getDepartmentsById(@PathVariable Long id) {
		Department department = null;
		
		try {
			department = departmentService.getDepartmentById(id);
		} catch (InvalidDepartmentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Department ID", e);
		}
		
		return department;
	}

}
