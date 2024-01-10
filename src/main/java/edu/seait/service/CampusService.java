package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidCampusException;
import edu.seait.model.Campus;


public interface CampusService {

	List<Campus> getAllCampuses();
	
	Campus getCampus(Long id) throws InvalidCampusException;

}