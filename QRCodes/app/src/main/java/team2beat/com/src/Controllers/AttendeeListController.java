package team2beat.com.src.Controllers;

import java.util.Date;

import team2beat.com.src.Models.*;

//Refactored - AttendeeListController moved to servlet on the server, therefore deprecated here

public class AttendeeListController
{
	public AttendeeListController()
	{}
		
	// Creates a new booking via the booking model
	public void CreateBooking ()
	{
			if (loginCheck())
			{
				// TODO: Unauthorised access message
			}
			
			// Needed Models
			LocationModel locationModel = new LocationModel ();
			AttendanceListModel attListModel = new AttendanceListModel();
			BookingModel bookingModel = new BookingModel();
						
			// Gain booking information from application_form
			
			//  AttendanceList information
			
			
			// Location information
			
			// Booking information
			String lectureID;
			Date startTime;
			Date endDate;
			String locationID;
			String staffID;
			String attendanceListID;
			
			AttendanceListModel newAttList = new AttendanceListModel();
			LocationModel newLocation = new LocationModel();
			BookingModel newBooking	= new BookingModel();
	}
		
	// Ensures user is logged in
	private boolean loginCheck ()
	{
		// Return true if incorrectly logged-in
		if (true)
		{ return true; }
		else
		{ return false; }
	}
}

