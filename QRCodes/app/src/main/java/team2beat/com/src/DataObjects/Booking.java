package team2beat.com.src.DataObjects;

import team2beat.com.src.DataObjects.Lecture;

import java.sql.Date;
import java.sql.Time;

public class Booking
{
	// Booking details
	private String bookingID;
	private String classID;
	private Time start;
	private Time end;
	private Date theDate;
	private String attListID;
	private String locationID;
	private String staffUsername;
	private Lecture lecture;



	// Constructor
	public Booking(String bookingID, Time start, Time end, Date theDate, String attListID, String locationID)
	{
		this.bookingID = bookingID;
		this.start = start;
		this.end = end;
		this.theDate = theDate;
		this.attListID = attListID;
		this.locationID = locationID;
	}

	// Constructor
	public Booking (String bookingID, String classID, Time start, Time end, Date theDate, String attListID, String locationID, String staffUsername)
	{
		this.bookingID = bookingID;
		this.classID = classID;
		this.start = start;
		this.end = end;
		this.theDate = theDate;
		this.attListID = attListID;
		this.locationID = locationID;
		this.staffUsername = staffUsername;
	}

	public void addLecture (String classID, String moduleID, Lecture.LectType lType)
	{
		lecture = new Lecture (classID, moduleId, lType);
	}
}
