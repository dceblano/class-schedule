package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidSchoolYearException;
import edu.seait.model.SchoolYear;

public interface SchoolYearService {

	List<SchoolYear> getAllSchoolyears();

	SchoolYear getSchoolYearById(Long id) throws InvalidSchoolYearException;

}