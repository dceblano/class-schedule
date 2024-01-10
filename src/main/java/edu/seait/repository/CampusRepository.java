package edu.seait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.seait.model.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long>{

	List<Campus> findByCampusCode(String campusCode);
	
	List<Campus> findByCampusName(String campusName);
	
}
