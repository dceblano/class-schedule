package edu.seait.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import edu.seait.dto.RoomDto;
import edu.seait.model.Room;

public class Testing {

	public static void main(String[] args) {

		Testing testing = new Testing();
		testing.doThis();

	}

	private void doThis() {
		List<Room> rooms = new ArrayList<>();

		Room r1 = new Room();
		r1.setId(1L);
		r1.setRoomName("Room 1");

		Room r2 = new Room();
		r2.setId(1L);
		r2.setRoomName("Room 2");

		rooms.add(r1);
		rooms.add(r2);

		List<RoomDto> roomsDto = rooms.stream().map(r -> convertToDto(r)).collect(Collectors.toList());

	}

	private RoomDto convertToDto(Room room) {
		ModelMapper modelMapper = new ModelMapper();
		RoomDto roomDto = modelMapper.map(room, RoomDto.class);
		return roomDto;
	}

}
