package team2beat.com.src.Controllers;

import android.view.View;

import team2beat.com.src.AsyncClasses.EndClassAsync;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Staff;
import team2beat.com.src.Models.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import team2beat.com.qrcodes.PresentRecord;
import team2beat.com.src.Models.BookingModel;

public class BookingController {
    private String returnable;
    public String theBookingID;

    public String createNewBooking(String theFlag, Staff deets) {
        // TODO: Get from form
        //String bookingID = "";
        //String classID = "";
        //Time start = null;
        //Time end = null;
        //Date theDate = null;
        //String attListID = "";
        //String locationID = "";
        //String staffUsername = "";


        int lec_id = 15;
        int loc_id = 24;
        String staff_id = deets.getStaffID();
        //String theFlag = "Create";              // Create or Update (i.e. start or end)
        //returnables = new String[2];

        BookingModel bm = new BookingModel(lec_id, loc_id, staff_id, theFlag);
        returnable = bm.createBooking();


        //int attendanceListID = bookingModel.createBooking (thisBooking);

        return returnable;
    }

    // Constructor
    public BookingController() {
    }

    public void endClass() {
        BookingModel bm = new BookingModel();
        bm.endClass(theBookingID);

    }


    // Sets the student as logged in
    public boolean setAttendance(PresentRecord pRecord) {
        try {
            BookingModel bookingModel = new BookingModel();

            // Update attendee status
            String[] retrieved = bookingModel.setStudentPresent(pRecord.studentID, pRecord.bookingID);

            if (retrieved != null) {
                if (retrieved[1].equals("true")) {

                    return true;

                } else {

                    return false;
                }


            } else {
                return false;

            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // Updates the reason for the student
    public void updateAttendanceReason(String studentID, int bookingID, String moduleID, String reason) {
        //BookingModel bookingModel = new BookingModel();

        // Updates the reason for the student
        //bookingModel.updateAttendanceReason(studentID, bookingID, moduleID, reason);
    }

    // Gets a list of all the bookings
    public void getBookingList(String staffID) {
        //BookingModel bookingModel = new BookingModel();

        //List<Booking> bookingList = bookingModel.getBookingList (staffID);

        // TODO: Redirect to booking view with list
    }
}
