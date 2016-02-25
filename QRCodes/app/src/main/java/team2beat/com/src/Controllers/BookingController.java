package team2beat.com.src.Controllers;

import java.sql.Date;
import java.sql.Time;

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

}
