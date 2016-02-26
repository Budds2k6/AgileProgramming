package team2beat.com.src.Models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;

import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Lecture;
import team2beat.com.src.DataObjects.Location;
import team2beat.com.src.DataObjects.Module;

//Refactored - code for BookingModel moved to servlet due to the necessity of them being on the server

public class BookingModel
{
	Connection _conn;

	// Constructor
	public BookingModel()
	{}

	private void fudgeMethod (Connection conn)
	{
		// FIXME: Replace method with actual connection string
		this._conn = conn;
	}

	// Creates a new booking
	public int createBooking (Booking thisBooking)
	{
		// TODO: Access the database, and return the ID
		return 0;
	}

	// Sets the student present
	public void setStudentPresent (String studentID, int bookingID)
	{
		// TODO: Access database, and set attendance
		CallableStatement callState = null;

		try
		{
			// FIXME: Replace with correct Stored Procedure call
			String query = "{call TEMP_PROCEDURE (?, ?)}";
			callState = _conn.prepareCall(query);

			callState.close();
		}
		catch (Exception e)
		{

		}
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

			//Refactored
			while (resultSet.next())
			{
				String bookingID = resultSet.getString(1);
				String lectureID = resultSet.getString(2);
				Time start = resultSet.getTime(3);
				Date theDate = resultSet.getDate(3);
				Time end = resultSet.getTime(4);
				String locationID = resultSet.getString(5);
				String staffName = resultSet.getString(6);
				String attListID = resultSet.getString(7);
				String moduleID = resultSet.getString(8);
				String moduleName = resultSet.getString(9);
				String lectType = resultSet.getString(10);
				String roomNo = resultSet.getString(11);
				String building = resultSet.getString(12);


				//Refactoring - initially details within BookingModel, location, module and lecture
				// now have their own data structures to store info
				Location thisLocation = new Location (locationID, roomNo, building);
				Module thisModule = new Module (moduleID, moduleName);
				Lecture thisLecture = new Lecture (lectureID, moduleID, Lecture.LectType.valueOf(lectType));

				Booking thisBooking = new Booking (start, end, theDate, attListID, thisLocation, thisModule, thisLecture);

				bookingList.add(thisBooking);
			}

			temp.close();
		}
		catch (Exception e)
		{
			// TODO: No results exception
		}

		return bookingList;
	}
}