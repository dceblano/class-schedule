package edu.seait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.seait.model.Campus;
import edu.seait.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	List<Department> findByDepartmentCode(String departmentCode);
	
	List<Department> findByDepartmentName(String departmentName);
	
	List<Department> findByCampus(Campus campus);

}
