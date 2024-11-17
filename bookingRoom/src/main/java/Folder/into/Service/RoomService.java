package Folder.into.Service;

import Folder.into.Domain.Room;
import Folder.into.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    
    //หา room และ ชื่อของ Department
    public List<Object[]> getRoomAndDepartmentNames() {
        return roomRepository.findRoomAndDepartmentNames();
    }
}