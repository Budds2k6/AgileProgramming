package team2beat.com.src.Controllers;

import android.view.View;

import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.Models.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import team2beat.com.qrcodes.PresentRecord;
import team2beat.com.src.Models.BookingModel;

public class BookingController
{
    private String [] returnables;


	public String [] createNewBooking ()
    {
        // TODO: Get from form
        String bookingID = "";
        String classID = "";
        Time start = null;
        Time end = null;
        Date theDate = null;
        String attListID = "";
        String locationID = "";
        String staffUsername = "";


        int lec_id = 15;
        int loc_id = 24;
        String staff_id = "SSE1325";
        String theFlag = "Create";
        returnables = new String[2];

        BookingModel bm = new BookingModel(lec_id,loc_id,staff_id,theFlag);
        while (returnables == null || returnables[0].equals("") ||  returnables[1].equals("")) {
            returnables = bm.returnedId;

        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Booking thisBooking = new Booking(bookingID, classID, start, end, theDate, attListID, locationID, staffUsername);


        //int attendanceListID = bookingModel.createBooking (thisBooking);

        return returnables;
    }

    // Constructor
    public BookingController ()
    {}

    public void endClass(String booking_id)
    {
        String theFlag = "Update";
        EndClass ec = new EndClass(booking_id,theFlag);

    }


    // Sets the student as logged in
    public void setAttendance (PresentRecord pRecord)
    {
        //BookingModel bookingModel = new BookingModel();

        // Update attendee status
        //bookingModel.setStudentPresent(pRecord.studentID, pRecord.bookingID);
    }

    // Updates the reason for the student
    public void updateAttendanceReason (String studentID, int bookingID, String moduleID, String reason)
    {
        //BookingModel bookingModel = new BookingModel();

        // Updates the reason for the student
        //bookingModel.updateAttendanceReason(studentID, bookingID, moduleID, reason);
    }

    // Gets a list of all the bookings
    public void getBookingList (String staffID)
    {
        //BookingModel bookingModel = new BookingModel();

        //List<Booking> bookingList = bookingModel.getBookingList (staffID);

        // TODO: Redirect to booking view with list
    }
}
