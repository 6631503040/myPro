package Folder.into.Controller;
import Folder.into.Domain.BookingData;
import Folder.into.Service.ApprovalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/approve")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;


    // ========================== TEACHER ==========================

    // Get bookings for teacher
    @GetMapping("/teacher/approve")
    public ResponseEntity<List<BookingData>> getBookingsForTeacher(@RequestHeader("Authorization") String token) {
        //เช็คว่า มี token มั้ย
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // ไม่มี token -> Unauthorized
        }

    List<BookingData> bookings = approvalService.getBookingsForTeacher(token);
    
    return bookings.isEmpty() ? 
        new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
        new ResponseEntity<>(bookings, HttpStatus.OK);
}

    // Approve booking for teacher
    @PutMapping("/teacher/approve/{bookingId}")
    public ResponseEntity<String> approveBookingByTeacher(@PathVariable Long bookingId) {
        try {
            String response = approvalService.TeacherapproveBooking(bookingId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // ========================== HEAD DEPARTMENT ==========================

    // Get bookings for head of department
    @GetMapping("/head-department/bookings")
    public ResponseEntity<List<BookingData>> getBookingsForHeadDepartment(@RequestHeader("Authorization") String token) {
        //เช็คว่า มี token มั้ย
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // ไม่มี token -> Unauthorized
        }

    List<BookingData> bookings = approvalService.getBookingsForHeadDepartment(token);
        return bookings.isEmpty() ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
            new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Approve booking for head of department
    @PutMapping("/head-department/approve/{bookingId}")
    public ResponseEntity<String> approveBookingByHeadDepartment(@PathVariable Long bookingId) {
        try {
            String response = approvalService.HeadDepApproveBooking(bookingId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // ========================== DIRECTOR ==========================

    // Get bookings for director
    @GetMapping("/director/bookings")
    public ResponseEntity<List<BookingData>> getBookingsForDirector(@RequestHeader("Authorization") String token) {
        //เช็คว่า มี token มั้ย
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // ไม่มี token -> Unauthorized
        }

        List<BookingData> bookings = approvalService.getBookingsForDirector(token);

        // ตรวจสอบผลลัพธ์
        return bookings.isEmpty() ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) : 
            new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Approve booking for director
    @PutMapping("/director/approve/{bookingId}")
    public ResponseEntity<String> approveBookingByDirector(@PathVariable Long bookingId) {
        try {
            String response = approvalService.DirectorApproveBooking(bookingId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // ========================== DENY BOOKING ==========================

    // Deny booking with a reason
    @PutMapping("/deny/{bookingId}")
    public ResponseEntity<String> denyBooking(@PathVariable Long bookingId, @RequestParam String reason) {
        try {
            String response = approvalService.denyBooking(bookingId, reason);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}
