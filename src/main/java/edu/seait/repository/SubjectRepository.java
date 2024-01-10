package edu.seait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.seait.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	List<Subject> findAll();

	List<Subject> findBySubjectCode(String subjectCode);

	List<Subject> findBySubjectName(String subjectName);

}
