package edu.seait.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidScheduleException;
import edu.seait.model.Schedule;
import edu.seait.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}

	public List<Schedule> getSchedulesByCampusIdAndRoomId(Long campusId, Long roomId) {
		return scheduleRepository.findScheduleByCampusIdAndRoomId(campusId, roomId);
	}

	public Schedule getSchedulesByCampusIdAndRoomIdAndScheduleId(Long campusId, Long roomId, Long scheduleId)
			throws InvalidScheduleException {
		Schedule schedule = scheduleRepository.findScheduleByCampusIdAndRoomIdAndScheduleId(campusId, roomId,
				scheduleId);
		if (schedule == null) {
			throw new InvalidScheduleException("Schedule you are looking for does not exist!");
		}
		return schedule;
	}

	public List<Schedule> getSchedulesByCampusIdAndFacultyId(Long campusId, Long facultyId) {
		return scheduleRepository.findScheduleByCampusIdAndFacultyId(campusId, facultyId);
	}

	public Schedule getSchedulesByCampusIdAndFacultyIdAndScheduleId(Long campusId, Long facultyId, Long scheduleId) {
		return scheduleRepository.findScheduleByCampusIdAndFacultyIdAndScheduleId(campusId, facultyId, scheduleId);
	}

	@Override
	public Schedule getScheduleById(Long id) throws InvalidScheduleException {
		Schedule schedule = scheduleRepository.findById(id)
				.orElseThrow(() -> new InvalidScheduleException("Schedule you are looking for does not exist!"));
		return schedule;

//		Optional<Schedule> schedules = scheduleRepository.findById(id);
//		if (schedules.isPresent()) {
//			return schedules.get();
//		} else {
//			throw new InvalidScheduleException("Schedule you are looking for does not exist!");
//		}
	}

	public Schedule saveSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}

	public void deleteSchedule(Schedule schedule) {
		scheduleRepository.delete(schedule);
	}

	public void deleteScheduleById(Long id) {
		scheduleRepository.deleteById(id);
	}

}
