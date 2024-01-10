package edu.seait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.seait.model.SchoolYear;

public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long> {

	List<SchoolYear> findByYear(String year);

}
