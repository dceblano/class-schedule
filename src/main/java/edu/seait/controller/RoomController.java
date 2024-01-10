package edu.seait.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.seait.dto.RoomDto;
import edu.seait.exception.InvalidRoomException;
import edu.seait.model.Room;
import edu.seait.service.RoomService;

@CrossOrigin
@RestController
public class RoomController {
	
	private static final Logger LOGGER = LogManager.getLogger(RoomController.class);

	@Autowired
	private RoomService roomService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/rooms")
	public List<RoomDto> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		List<RoomDto> roomsDto = rooms.stream().map(r -> convertToDto(r)).collect(Collectors.toList());
		return roomsDto;
	}

	@GetMapping(value = "/rooms/{id}")
	public RoomDto getRoomById(@PathVariable Long id) {
		RoomDto roomDto = null;

		try {
			Room room = roomService.getRoomById(id);
			roomDto = convertToDto(room);
		} catch (InvalidRoomException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, null);
		}

		return roomDto;
	}
	
	@GetMapping(value = "/campuses/{campusId}/rooms/{roomId}")
	public RoomDto getRoomByCampusIdAndRoomId(@PathVariable Long campusId, @PathVariable Long roomId) {
		RoomDto roomDto = null;

		try {
			Room room = roomService.getRoomByCampusIdAndRoomid(campusId, roomId);
			roomDto = convertToDto(room);
		} catch (InvalidRoomException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, null);
		}

		return roomDto;
	}

	@GetMapping(value = "/campuses/{id}/rooms", produces = "application/json")
	public List<RoomDto> getAllRoomsByCampusId(@PathVariable Long id) {
		LOGGER.debug(">>> " + id);
		List<Room> rooms = roomService.getAllRoomsByCampusId(id);
		List<RoomDto> roomsDto = rooms.stream().map(r -> convertToDto(r)).collect(Collectors.toList());
		return roomsDto;
	}

	private RoomDto convertToDto(Room room) {
		RoomDto roomDto = modelMapper.map(room, RoomDto.class);
		roomDto.setCampusId(room.getCampus().getId());
		roomDto.setCampusName(room.getCampus().getCampusName());
		return roomDto;
	}

	private Room convertToObject(RoomDto roomDto) {
		Room room = modelMapper.map(roomDto, Room.class);
		return room;
	}

}
