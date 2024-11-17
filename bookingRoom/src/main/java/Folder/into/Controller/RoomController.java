package Folder.into.Controller;

import Folder.into.Repository.RoomRepository;
import Folder.into.Domain.Room;
import Folder.into.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;


    //สำหรับหน้า Show rooms
    @GetMapping("/allrooms")
    public ResponseEntity<List<Object[]>> getRoomDetails() {
        List<Object[]> roomDetails = roomService.getRoomAndDepartmentNames();
        if (roomDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomDetails, HttpStatus.OK);
    }
}
