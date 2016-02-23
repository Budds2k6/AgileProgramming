// import.DataModels_Stuff

public class BookingController ()
{
	public BookingController ()
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
			
			AttendanceList newAttList = new AttendanceList();
			Location newLocation = new Location ();
			Booking newBooking	= new Booking ();	
	}
		
	// Ensures user is logged in
	private bool loginCheck ()
	{
		// Return true if incorrectly logged-in
		if (true)
		{ return true; }
		else
		{ return false; }
	}
}

