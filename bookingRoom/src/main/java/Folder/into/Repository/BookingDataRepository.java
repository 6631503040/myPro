package Folder.into.Repository;

import Folder.into.Domain.BookStatus;
import Folder.into.Domain.BookingData;
import Folder.into.Domain.Room;
import Folder.into.Domain.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingDataRepository extends JpaRepository<BookingData, Long> {
    List<BookingData> findAll();

    Optional<BookingData> findByRefId(Long refId);

    List<BookingData> findByUsers_UsersId(Long usersId);

    List<BookingData> findByUsers_UsersNameAndBookStatus(String usersName, BookStatus bookStatus);

    // ค้นหา Booking จาก ชื่อ teacher
    @Query("SELECT b FROM BookingData b JOIN users u WHERE u.usersName = :teacherName")
    List<BookingData> findByteacherName(@Param("teacherName") String teacherName);

    // ค้นหา Booking จาก ชื่อ HeadDepartment และ BookStatus
    @Query("SELECT b FROM BookingData b JOIN b.room r JOIN r.department d JOIN d.headDepId h WHERE h.usersName = :headName AND b.bookStatus = :bookStatus")
    List<BookingData> findBookingsByHeadDepartmentNameAndBookStatus(@Param("headName") String headName,
            @Param("bookStatus") BookStatus bookStatus);

    // ค้นหา ห้องว่างในช่วงเวลาที่เลือก
    @Query("SELECT b FROM BookingData b WHERE b.room = :room AND " +
            "(:startDate BETWEEN b.dateStart AND b.dateEnd OR " +
            ":endDate BETWEEN b.dateStart AND b.dateEnd OR " +
            "b.dateStart BETWEEN :startDate AND :endDate OR " +
            "b.dateEnd BETWEEN :startDate AND :endDate)")
    List<BookingData> findConflictingBookings(
            @Param("room") Room room,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}