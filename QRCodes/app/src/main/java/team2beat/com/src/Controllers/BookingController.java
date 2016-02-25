package team2beat.com.src.Controllers;

import team2beat.com.qrcodes.PresentRecord;
import team2beat.com.src.Models.BookingModel;

public class BookingController
{
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
