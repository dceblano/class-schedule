package edu.seait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.seait.dto.ScheduleDto;
import edu.seait.exception.InvalidDepartmentException;
import edu.seait.exception.InvalidScheduleException;
import edu.seait.model.Class;
import edu.seait.model.Department;
import edu.seait.model.Faculty;
import edu.seait.model.Room;
import edu.seait.model.Schedule;
import edu.seait.model.SchoolYear;
import edu.seait.model.Subject;
import edu.seait.service.DepartmentService;
import edu.seait.service.ScheduleService;

@CrossOrigin
@RestController
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private DepartmentService departmentService;

	@GetMapping(value = "/schedules")
	public List<ScheduleDto> getAllSchedules() {
		List<Schedule> schedules = scheduleService.getAllSchedules();
		List<ScheduleDto> schedulesDto = schedules.stream().map(s -> convertToDto(s)).toList();
		return schedulesDto;
	}

	@GetMapping(value = "/campuses/{campusId}/rooms/{roomId}/schedules")
	public List<ScheduleDto> getSchedulesByCampusIdAndRoomId(@PathVariable Long campusId, @PathVariable Long roomId) {
		List<Schedule> schedules = scheduleService.getSchedulesByCampusIdAndRoomId(campusId, roomId);
		List<ScheduleDto> schedulesDto = schedules.stream().map(s -> convertToDto(s)).toList();
		return schedulesDto;
	}

	@GetMapping(value = "/campuses/{campusId}/rooms/{roomId}/schedules/{scheduleId}")
	public ScheduleDto getSchedulesByCampusIdAndRoomIdAndScheduleId(@PathVariable Long campusId,
			@PathVariable Long roomId, @PathVariable Long scheduleId) {
		try {
			Schedule schedule = scheduleService.getSchedulesByCampusIdAndRoomIdAndScheduleId(campusId, roomId,
					scheduleId);
			ScheduleDto schedulesDto = convertToDto(schedule);
			return schedulesDto;
		} catch (InvalidScheduleException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Schedule ID", e);
		}
	}

	@PostMapping(value = "/campuses/{campusId}/rooms/{roomId}/schedules", consumes = "application/json", produces = "application/json")
	public ScheduleDto addNewSchedule(@PathVariable Long campusId, @PathVariable Long roomId,
			@RequestBody ScheduleDto dto) {

		dto.setCampusId(campusId); 
		dto.setRoomId(roomId);
		Schedule schedule = convertToObject(dto);
		schedule = scheduleService.saveSchedule(schedule);

		return convertToDto(schedule);
	}

	@DeleteMapping(value = "/campuses/{campusId}/rooms/{roomId}/schedules/{scheduleId}")
	public String deleteScheduleByCampusIdAndRoomIdAndScheduleId(@PathVariable Long campusId, @PathVariable Long roomId,
			@PathVariable Long scheduleId) {
		try {
			Schedule schedule = scheduleService.getSchedulesByCampusIdAndRoomIdAndScheduleId(campusId, roomId,
					scheduleId);
			scheduleService.deleteSchedule(schedule);
			return "success";
		} catch (InvalidScheduleException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Schedule ID", e);
		}
	}

	@GetMapping(value = "/campuses/{campusId}/faculties/{facultyId}/schedules")
	public List<ScheduleDto> getSchedulesByCampusIdAndFacultyId(@PathVariable Long campusId,
			@PathVariable Long facultyId) {
		List<Schedule> schedules = scheduleService.getSchedulesByCampusIdAndFacultyId(campusId, facultyId);
		List<ScheduleDto> schedulesDto = schedules.stream().map(s -> convertToDto(s)).toList();
		return schedulesDto;
	}

	@GetMapping(value = "/campuses/{campusId}/faculties/{facultyId}/schedules/{scheduleId}")
	public ScheduleDto getSchedulesByCampusIdAndFacultyIdIdAndScheduleId(@PathVariable Long campusId,
			@PathVariable Long facultyId, @PathVariable Long scheduleId) {
		Schedule schedule = scheduleService.getSchedulesByCampusIdAndFacultyIdAndScheduleId(campusId, facultyId,
				scheduleId);
		ScheduleDto schedulesDto = convertToDto(schedule);
		return schedulesDto;
	}

	@GetMapping(value = "/schedules/{id}")
	public ScheduleDto getScheduleById(@PathVariable Long id) {
		ScheduleDto scheduleDto = null;
		try {
			Schedule schedule = scheduleService.getScheduleById(id);
			scheduleDto = convertToDto(schedule);
		} catch (InvalidScheduleException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Schedule ID", e);
		}

		return scheduleDto;
	}

	@PutMapping(value = "/schedules/{id}", consumes = "application/json", produces = "application/json")
	public ScheduleDto saveSchedule(@RequestBody ScheduleDto dto, @PathVariable Long id) {

		Schedule schedule = convertToObject(dto);
		schedule.setId(id);
		schedule = scheduleService.saveSchedule(schedule);

		return convertToDto(schedule);
	}

	@DeleteMapping(value = "/schedules/{id}")
	public String deleteSchedule(@PathVariable Long id) {
		try {
			Schedule schedule = scheduleService.getScheduleById(id);
			scheduleService.deleteSchedule(schedule);
			return "success";
		} catch (InvalidScheduleException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provide valid Schedule ID", e);
		}
	}

	private ScheduleDto convertToDto(Schedule schedule) {
		ScheduleDto dto = new ScheduleDto();

		dto.setCampusId(schedule.getDepartment().getCampus().getId());
		dto.setCampusName(schedule.getDepartment().getCampus().getCampusName());
		dto.setClassId(schedule.getClazz().getId());
		dto.setClassName(schedule.getClazz().getClassName());
		dto.setDays(schedule.getDays());
		dto.setDepartmentId(schedule.getDepartment().getId());
		dto.setDepartmentName(schedule.getDepartment().getDepartmentName());
		dto.setEncodedBy(schedule.getEncodedBy());
		dto.setEndTime(schedule.getEndTime());
		dto.setFacultyId(schedule.getFaculty().getId());
		dto.setFacultyName(schedule.getFaculty().getFirstname() + " " + schedule.getFaculty().getMiddlename() + " "
				+ schedule.getFaculty().getLastname());
		dto.setId(schedule.getId());
		dto.setRoomId(schedule.getRoom().getId());
		dto.setRoomName(schedule.getRoom().getRoomName());
		dto.setSchoolYear(schedule.getSchoolYear().getYear());
		dto.setSchoolYearId(schedule.getSchoolYear().getId());
		dto.setStartTime(schedule.getStartTime());
		dto.setSubjectId(schedule.getSubject().getId());
		dto.setSubjectName(schedule.getSubject().getSubjectName());

		return dto;
	}

	private Schedule convertToObject(ScheduleDto dto) {
		Schedule schedule = new Schedule();

		edu.seait.model.Class clazz = new Class();
		clazz.setId(dto.getClassId());
		schedule.setClazz(clazz);

		schedule.setDays(dto.getDays());

		Department department = null;
		try {
			department = departmentService.getDepartmentById(dto.getDepartmentId());
		} catch (InvalidDepartmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schedule.setDepartment(department);

		schedule.setEncodedBy(dto.getEncodedBy());

		schedule.setEndtime(dto.getEndTime());

		Faculty faculty = new Faculty();
		faculty.setId(dto.getFacultyId());
		schedule.setFaculty(faculty);

		schedule.setId(dto.getId());

		Room room = new Room();
		room.setId(dto.getRoomId());
		schedule.setRoom(room);

		SchoolYear schoolYear = new SchoolYear();
		schoolYear.setId(dto.getSchoolYearId());
		schedule.setSchoolYear(schoolYear);

		schedule.setStartTime(dto.getStartTime());

		Subject subject = new Subject();
		subject.setId(dto.getSubjectId());
		schedule.setSubject(subject);

		return schedule;
	}

}
