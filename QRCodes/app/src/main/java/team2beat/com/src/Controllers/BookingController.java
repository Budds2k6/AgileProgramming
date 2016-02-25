package team2beat.com.src.Controllers;

import java.sql.Date;
import java.sql.Time;

=======
import team2beat.com.qrcodes.PresentRecord;
import team2beat.com.src.Models.BookingModel;

public class BookingController
{
	public void createNewBooking ()
    {
        // TODO: Get from form
        String bookingID = "";
        String classID = "";
        // Date start = "";
        Time start = null;
        Time end = null;
        Date theDate = null;
        String attListID = "";
        String locationID = "";
        String staffUsername = "";

        Booking thisBooking = new Booking(bookingID, classID, start, end, theDate, attListID, locationID, staffUsername);

        BookingModel bookingModel = new BookingModel();

        bookingModel.createBooking (thisBooking);
    }

    // Constructor
    public BookingController ()
    {}

    // Sets the student as logged in
    public void setAttendance (PresentRecord pRecord, int bookingID, String moduleID)
    {
        BookingModel bookingModel = new BookingModel();

        // Update attendee status
        bookingModel.setStudentPresent(pRecord.studentID, bookingID, moduleID);
    }

    // Updates the reason for the student
    public void updateAttendanceReason (String studentID, int bookingID, String moduleID, String reason)
    {
        BookingModel bookingModel = new BookingModel();

        // Updates the reason for the student
        bookingModel.updateAttendanceReason(studentID, bookingID, moduleID, reason);
    }

    // Gets a list of all the bookings
    public void getBookingList (String staffID)
    {
        BookingModel bookingModel = new BookingModel();

        //List<Booking> bookingList = bookingModel.getBookingList (staffID);

        // TODO: Redirect to booking view with list
    }
}
