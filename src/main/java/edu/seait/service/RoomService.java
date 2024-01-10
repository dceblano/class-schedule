package edu.seait.service;

import java.util.List;

import edu.seait.exception.InvalidRoomException;
import edu.seait.model.Room;

public interface RoomService {

	List<Room> getAllRooms();

	List<Room> getAllRoomsByCampusId(Long campusId);

	Room getRoomByCampusIdAndRoomid(Long campusId, Long roomId) throws InvalidRoomException;

	Room getRoomById(Long id) throws InvalidRoomException;

}