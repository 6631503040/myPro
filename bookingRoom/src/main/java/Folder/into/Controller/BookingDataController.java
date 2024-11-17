package Folder.into.Controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Folder.into.Domain.BookingData;
import Folder.into.Repository.BookingDataRepository;
import Folder.into.Service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingDataController {

    @Autowired
    private BookingDataRepository bookingrepo;
    @Autowired
    private BookingService bookingservice;

    // ดูประวัติ Booking #โชว์ทุก Booking
    @GetMapping("/history")
    public List<BookingData> viewBookingHistory() {
        return bookingrepo.findAll();
    }

    // สร้าง Booking
    @PostMapping("/request")
    public BookingData requestBooking(@RequestBody BookingData bookingData) {
        return bookingservice.createBookingRequest(bookingData);
    }

    // Delete Booking
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        Optional<BookingData> booking = bookingrepo.findById(bookingId);
        if (booking.isEmpty()) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }
        if ("Approved".equals(booking.get().getBookStatus().toString())) {
            return new ResponseEntity<>("Cannot delete an approved booking", HttpStatus.FORBIDDEN);
        }
        bookingservice.delectbooking(bookingId);
        return new ResponseEntity<>("Booking deleted successfully", HttpStatus.OK);
    }

}
