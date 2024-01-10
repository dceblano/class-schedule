package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidScheduleException;
import edu.seait.model.Schedule;

public interface ScheduleService {

	List<Schedule> getAllSchedules();

	List<Schedule> getSchedulesByCampusIdAndRoomId(Long campusId, Long roomId);

	Schedule getSchedulesByCampusIdAndRoomIdAndScheduleId(Long campusId, Long roomId, Long scheduleId)
			throws InvalidScheduleException;

	List<Schedule> getSchedulesByCampusIdAndFacultyId(Long campusId, Long facultyId);

	Schedule getSchedulesByCampusIdAndFacultyIdAndScheduleId(Long campusId, Long facultyId, Long scheduleId);

	Schedule getScheduleById(Long id) throws InvalidScheduleException;

	Schedule saveSchedule(Schedule schedule);

	void deleteSchedule(Schedule schedule);

	void deleteScheduleById(Long id);

}