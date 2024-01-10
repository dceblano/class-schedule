package edu.seait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidSchoolYearException;
import edu.seait.model.SchoolYear;
import edu.seait.repository.SchoolYearRepository;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {
	
	@Autowired
	private SchoolYearRepository schoolYearRepository;
	
	@Override
	public List<SchoolYear> getAllSchoolyears() {
		return schoolYearRepository.findAll();
	}
	
	@Override
	public SchoolYear getSchoolYearById(Long id) throws InvalidSchoolYearException {
		Optional<SchoolYear> schoolYears = schoolYearRepository.findById(id);
		if (schoolYears.isPresent()) {
			return schoolYears.get();
		} else {
			throw new InvalidSchoolYearException("School Year you are looking for does not exist!");
		}
	}

}
