package edu.seait.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.seait.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByRoomName(String roomName);
	
	@Query("select r from Room r where r.campus.id = :campusId")
	List<Room> findRoomsByCampusId(Long campusId);
	
	
	@Query("select r from Room r where r.campus.id = :campusId and r.id = :roomId")
	Room findRoomByCampusIdAndRoomId(Long campusId, Long roomId);

}
