package edu.seait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.seait.exception.InvalidRoomException;
import edu.seait.model.Room;
import edu.seait.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}
	
	public List<Room> getAllRoomsByCampusId(Long campusId) {
		return roomRepository.findRoomsByCampusId(campusId);
	}
	
	public Room getRoomByCampusIdAndRoomid(Long campusId, Long roomId) throws InvalidRoomException {
		return roomRepository.findRoomByCampusIdAndRoomId(campusId, roomId);
	}

	@Override
	public Room getRoomById(Long id) throws InvalidRoomException {
		Optional<Room> rooms = roomRepository.findById(id);
		if (rooms.isPresent()) {
			return rooms.get();
		} else {
			throw new InvalidRoomException("Room you are looking for does not exist!");
		}
	}

}
