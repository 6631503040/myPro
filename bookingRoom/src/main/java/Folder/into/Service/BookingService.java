package Folder.into.Service;

import Folder.into.Domain.BookStatus;
import Folder.into.Domain.BookingData;
import Folder.into.Domain.Room;
import Folder.into.Repository.BookingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;


@Service
public class BookingService {

    @Autowired
    private BookingDataRepository bookingRepository;

    // ================================================= save Bookingrequest =================================================
    public BookingData createBookingRequest(BookingData bookingData) {
        boolean roomAvailable = checkRoomAvailability(bookingData.getRoom(), bookingData.getDateStart(), bookingData.getDateEnd());
    if (!roomAvailable) {
        throw new RuntimeException("Room is not available for the selected time.");
    }
        return bookingRepository.save(bookingData);
    }

    private boolean checkRoomAvailability(Room room, LocalDate startDate, LocalDate endDate) {
    // ตรวจสอบว่าห้องว่างในช่วงเวลาที่เลือก
        return bookingRepository.findConflictingBookings(room, startDate, endDate).isEmpty(); 
    }

    // ================================================= Booking history =================================================
    public List<BookingData> getBookingHistory(Long usersId) {
        return bookingRepository.findByUsers_UsersId(usersId);
    }

    // ================================================= DELECT BOOKING REQUEST =================================================
    public void delectbooking(Long bookingId) {
        // เช็ตว่า delect ไปแล้วรึยัง
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
        } else {
            throw new RuntimeException("Booking not found with id: " + bookingId);
        }
    }


    //================================================== PDF =========================================================
    public byte[] generatePDF(Long bookingId) throws IOException {
        BookingData bookingData = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Add a page
            PDPage page = new PDPage();
            document.addPage(page);

            // Write content to the page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
   
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                // Header
                contentStream.showText("แบบคำขอการจองห้องนอกเวลาออนไลน์");
                contentStream.newLine();
                contentStream.newLine();

                // Booking Details

                contentStream.showText("ชื่อผู้ส่งคำขอ: " + bookingData.getUsers().getUsersName());
                contentStream.newLine();
                contentStream.showText("รหัสประจำตัว: " + bookingData.getUsers().getUsersId());
                contentStream.newLine();
                contentStream.showText("เบอร์ติดต่อ: " + bookingData.getUsers().getUsersPhone());
                contentStream.newLine();
                contentStream.showText("หัวข้อ/รายวิชา: " + bookingData.getTopic());
                contentStream.newLine();
                contentStream.showText("ห้อง: " + bookingData.getRoom());
                contentStream.newLine();
                contentStream.showText("วันที่เริ่ม: " + bookingData.getDateStart());
                contentStream.newLine();
                contentStream.showText("วันที่สิ้นสุด: " + bookingData.getDateEnd());
                contentStream.newLine();
                contentStream.showText("เวลา: " + bookingData.getTimeStart() + " - " + bookingData.getTimeEnd());
                contentStream.newLine();
                contentStream.newLine();

                // Approvers Section
                contentStream.showText("ผู้อนุมัติ");
                contentStream.newLine();
                contentStream.showText("ผู้อนุมัติที่ 1: " + bookingData.getTeacherName().getUsersName() + " (" + bookingData.getAppDateTeacher() + ")");
                contentStream.newLine();
                contentStream.showText("ผู้อนุมัติที่ 2: " + bookingData.getDirectorName().getUsersName() + " (" + bookingData.getAppDateHead() + ")");
                contentStream.newLine();

                contentStream.endText();
            }

            // Save document to byte array
            document.save(out);
            return out.toByteArray();
        }
    }
}

