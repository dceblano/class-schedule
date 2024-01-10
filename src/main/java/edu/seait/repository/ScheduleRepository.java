package edu.seait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.seait.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query("select s from Schedule s where s.department.campus.id = :campusId and s.room.id = :roomId")
	List<Schedule> findScheduleByCampusIdAndRoomId(Long campusId, Long roomId);
	
	@Query("select s from Schedule s where s.department.campus.id = :campusId and s.room.id = :roomId and s.id = :scheduleId")
	Schedule findScheduleByCampusIdAndRoomIdAndScheduleId(Long campusId, Long roomId, Long scheduleId);

	@Query("select s from Schedule s where s.department.campus.id = :campusId and s.faculty.id = :facultyId")
	List<Schedule> findScheduleByCampusIdAndFacultyId(Long campusId, Long facultyId);

	@Query("select s from Schedule s where s.department.campus.id = :campusId and s.faculty.id = :facultyId and s.id = :scheduleId")
	Schedule findScheduleByCampusIdAndFacultyIdAndScheduleId(Long campusId, Long facultyId, Long scheduleId);
	
}
