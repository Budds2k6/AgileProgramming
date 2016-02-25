package team2beat.com.src.Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;

import team2beat.com.src.DataObjects.Booking;

public class BookingModel
{
	Connection _conn;

	// Constructor
	public BookingModel()
	{
		// TODO: Remove from constructor parameter, and make static
		this._conn = conn;
	}

	private void FudgeMethod (Connection conn)
	{
		this._conn = conn;
	}

	// Creates a new booking
	public int createBooking (Booking thisBooking)
	{
		// TODO: Access the database, and return the ID
		return 0;
	}

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
	public List<Booking> getBookingList (String staffID)
	{
		// TODO: Un-fudge
		Statement temp = null;
		String query = "SELECT * FROM Booking";

		List<Booking> bookingList = new ArrayList<Booking>();

		try
		{
			temp = _conn.createStatement();

			ResultSet resultSet = temp.executeQuery(query);

			while (resultSet.next())
			{
				String bookingID = resultSet.getString(1);
				String lectureID = resultSet.getString(2);
				Time start = resultSet.getTime(3);
				Time end = resultSet.getTime(4);
				String locationID = resultSet.getString(5);
				// result 6 is redundant
				String attListID = resultSet.getString(7);
				String staffName = resultSet.getString(8);
				String moduleName = resultSet.getString(9);
				String lectType = resultSet.getString(10);
				String roomNo = resultSet.getString(11);
				String building = resultSet.getString(12);


			}
		}
		catch (Exception e)
		{

		}

		return bookingList;
	}
}