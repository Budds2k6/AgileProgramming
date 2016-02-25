package team2beat.com.src.Models;

public class BookingModel
{
	// Constructor
	public BookingModel()
	{}

	// Sets the student present
	public void setStudentPresent (String studentID, int bookingID, String moduleID)
	{
		// TODO: Access database, and set attendance
	}

	// Set the reason for absence
	public void updateAttendanceReason (String studentID, int bookingID, String moduleID, String reason)
	{
		// TODO: Access the database, and set reason
	}

	// Obtains a list of bookings
	public void getBookingList (String staffID)
	{
		// TODO: Access the database, and collect the booking list
		// Return the booking list for given lecturer
	}
}