package edu.seait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidCampusException;
import edu.seait.model.Campus;
import edu.seait.repository.CampusRepository;

@Service
public class CampusServiceImpl implements CampusService {

	@Autowired
	private CampusRepository campusRepository;

	@Override
	public List<Campus> getAllCampuses() {
		return (List<Campus>) campusRepository.findAll();
	}

	public Campus getCampus(Long id) throws InvalidCampusException {
		Optional<Campus> campus = campusRepository.findById(id);
		if (campus.isPresent()) {
			return campus.get();
		} else {
			throw new InvalidCampusException("Campus you are looking for does not exist!");
		}
	}

}
