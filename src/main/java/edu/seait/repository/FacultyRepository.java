package edu.seait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.seait.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

	List<Faculty> findByIdNumber(String idNumber);

	List<Faculty> findByLastname(String lastname);

	List<Faculty> findByFirstname(String firstname);

	List<Faculty> findByMiddlename(String middlename);

	List<Faculty> findByEmailAddress(String emailAddress);

	List<Faculty> findByContact(String contact);

	List<Faculty> findByCategory(Long category);

	List<Faculty> findByUsername(String username);

	@Query("select f from Faculty f where f.campus.id = :campusId")
	List<Faculty> findFacultyByCampusId(Long campusId);

}
