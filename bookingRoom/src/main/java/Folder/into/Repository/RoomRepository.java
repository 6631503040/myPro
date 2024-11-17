package Folder.into.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import Folder.into.Domain.Room;
import java.util.List;


public interface RoomRepository extends JpaRepository<Room,Long>{
    @Query("SELECT r FROM Room r WHERE r.roomStatus = 'available'")
    List<Room> findAvailableRooms(String date, String time);


    //ใช้สำหรับหาห้อง
    @Query("SELECT r.roomId, r.roomNum, r.capacity, r.roomStatus, d.depName, b.buildName FROM Room r JOIN r.department d JOIN r.build b")
    List<Object[]> findRoomAndDepartmentNames();



}