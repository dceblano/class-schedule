package edu.seait.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.seait.model.Class;

public interface ClassRepository extends CrudRepository<Class, Long> {

	List<Class> findByClassName(String className);

}
