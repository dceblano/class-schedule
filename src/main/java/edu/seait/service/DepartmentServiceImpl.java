package edu.seait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidDepartmentException;
import edu.seait.model.Department;
import edu.seait.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(Long id) throws InvalidDepartmentException {
		Optional<Department> departments = departmentRepository.findById(id);
		if (departments.isPresent()) {
			return departments.get();
		} else {
			throw new InvalidDepartmentException("Department you are looking for does not exist!");
		}
	}

}
