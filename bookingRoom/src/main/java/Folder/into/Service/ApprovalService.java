package Folder.into.Service;

import Folder.into.Domain.BookStatus;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Folder.into.Domain.BookingData;
import Folder.into.Domain.Department;
import Folder.into.Domain.Room;
import Folder.into.Domain.RoomStatus;
import Folder.into.Domain.Users;
import Folder.into.Repository.BookingDataRepository;
import Folder.into.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class ApprovalService {
        @Autowired
        private BookingDataRepository bookingRepository;

        @Autowired
        private UserRepository usersRepository;

        // ================================================= TEACHER
        // =================================================

        // หา Booking ที่จะ Approve สำหรับ Teacher
        public List<BookingData> getBookingsForTeacher(String token) {
                Users loggedInUser = usersRepository.findByToken(token)
                                .orElseThrow(() -> new RuntimeException("Invalid Token"));
                return bookingRepository.findByteacherName(loggedInUser.getUsersName());
        }

        // สำหรับ การ Approve ของ Teacher
        public String TeacherapproveBooking(Long bookingId) {
                BookingData booking = bookingRepository.findById(bookingId)
                                .orElseThrow(() -> new RuntimeException("Booking not found"));
                booking.setAppDateTeacher(LocalDate.now());
                // set time
                booking.setBookStatus(BookStatus.TEACHER_APPROVED);
                bookingRepository.save(booking);
                return "Booking approved successfully";
        }

        // ================================================= HEAD DEPARTMENT
        // =================================================

        // หา Booking ที่จะ Approve สำหรับ Head Department
        public List<BookingData> getBookingsForHeadDepartment(String token) {
                // ค้นหาผู้ใช้จาก Token
                Users loggedInUser = usersRepository.findByToken(token)
                                .orElseThrow(() -> new RuntimeException("Invalid Token"));

                return bookingRepository.findBookingsByHeadDepartmentNameAndBookStatus(loggedInUser.getUsersName(),BookStatus.TEACHER_APPROVED);
        }

        // สำหรับ การ Approve ของ Head Department
        public String HeadDepApproveBooking(Long bookingId) {
                BookingData booking = bookingRepository.findById(bookingId)
                                .orElseThrow(() -> new RuntimeException("Booking not found"));
                booking.setAppDateTeacher(LocalDate.now());
                booking.setBookStatus(BookStatus.HEAD_DEPARTMENT_APPROVED);
                bookingRepository.save(booking);
                return "Booking approved successfully";
        }

        // ================================================= DIRECTOR =================================================

        // //หา Booking ที่จะ Approve สำหรับ Director
        public List<BookingData> getBookingsForDirector(String token) {
                // ค้นหาผู้ใช้จาก Token
                Users loggedInUser = usersRepository.findByToken(token)
                                .orElseThrow(() -> new RuntimeException("Invalid Token"));

                // ค้นหา Booking ตามข้อมูลผู้ใช้
                return bookingRepository.findByUsers_UsersNameAndBookStatus(loggedInUser.getUsersName(),BookStatus.HEAD_DEPARTMENT_APPROVED);
        }

        // สำหรับ การ Approve ของ Director
        public String DirectorApproveBooking(Long bookingId) {
                BookingData booking = bookingRepository.findById(bookingId)
                                .orElseThrow(() -> new RuntimeException("Booking not found"));
                booking.setAppDateTeacher(LocalDate.now());
                booking.setBookStatus(BookStatus.APPROVED);

                // อัพเดทสถานะของห้อง not avaliable
                Room room = booking.getRoom();
                room.setRoomStatus(RoomStatus.NOT_AVAILABLE);
                bookingRepository.save(booking);
                return "Booking approved successfully";
        }

        // ================================================= DENY BOOKING REQUEST
        // =================================================
        public String denyBooking(Long bookingId, String reason) {
                BookingData booking = bookingRepository.findById(bookingId)
                                .orElseThrow(() -> new RuntimeException("Booking not found"));

                // อัพเดทสถานะของห้อง Denied
                booking.setBookStatus(BookStatus.DENIED);
                booking.setReason(reason);
                bookingRepository.save(booking);
                return "Booking denied successfully";
        }

}
