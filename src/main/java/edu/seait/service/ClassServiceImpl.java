package edu.seait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidClassException;
import edu.seait.model.Class;
import edu.seait.repository.ClassRepository;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private ClassRepository classRepository;

	@Override
	public List<Class> getAllClasses() {
		return (List<Class>) classRepository.findAll();
	}

	@Override
	public Class getClassById(Long id) throws InvalidClassException {
		Optional<Class> classes = classRepository.findById(id);
		if (classes.isPresent()) {
			return classes.get();
		} else {
			throw new InvalidClassException("Class you are looking for does not exist!");
		}
	}

}
