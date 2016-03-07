package team2beat.com.src.Models;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import team2beat.com.src.AsyncClasses.BookingAsync;
import team2beat.com.src.AsyncClasses.EndClassAsync;
import team2beat.com.src.AsyncClasses.RegisterAsync;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Lecture;
import team2beat.com.src.DataObjects.Location;
import team2beat.com.src.DataObjects.Module;

//Refactored - code for BookingModel moved to servlet due to the necessity of them being on the server

public class BookingModel
{
	Connection _conn;
	String lec_id;
	String loc_id;
	String staff_id;
	String theFlag;
	public String [] returnedId;
	public String [] registerSuccess;

	// Constructor
	public BookingModel(int lec,int loc, String sid, String flag)
	{
		this.lec_id = String.valueOf(lec);
		this.loc_id = String.valueOf(loc);
		this.staff_id = sid;
		this.theFlag = flag;

		//new PostClass().execute(lec_id,loc_id,staff_id,theFlag);
	}

	public BookingModel(){}

	private void fudgeMethod (Connection conn)
	{
		// FIXME: Replace method with actual connection string
		this._conn = conn;
	}

	// Creates a new booking
	//public int createBooking (Booking thisBooking)

	public String createBooking ()
	{
		// TODO: Access the database, and return the ID
		// DONE

		BookingAsync ba = new BookingAsync(lec_id,loc_id,staff_id,theFlag);

		while (returnedId == null || returnedId[0] == null || returnedId[1] == null || returnedId[0].equals("") ||  returnedId[1].equals("")) {
			returnedId = ba.returnedId;

		}


		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//int attendanceListID = bookingModel.createBooking (thisBooking);
		String theBookingID = returnedId[1];
		return theBookingID;
	}

	// Sets the student present
	public String [] setStudentPresent (String studentID, int bookingID)
	{
		// TODO: Access database, and set attendance
		//CallableStatement callState = null;

		try
		{
			// FIXME: Replace with correct Stored Procedure call
			//String query = "{call TEMP_PROCEDURE (?, ?)}";
			//callState = _conn.prepareCall(query);

			//callState.close();
			String bid = String.valueOf(bookingID);
			RegisterAsync ra = new RegisterAsync(bid, studentID);

			while (registerSuccess == null || registerSuccess[0] == null || registerSuccess[1] == null || registerSuccess[0].equals("") ||  registerSuccess[1].equals("")) {
				registerSuccess = ra.toReturn;

			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}



		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return registerSuccess;
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

	public void endClass(String booking_id){
		EndClassAsync eca = new EndClassAsync(booking_id,"Update");
	}
}