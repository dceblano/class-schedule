package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidDepartmentException;
import edu.seait.model.Department;

public interface DepartmentService {

	List<Department> getAllDepartment();

	Department getDepartmentById(Long id) throws InvalidDepartmentException;

}