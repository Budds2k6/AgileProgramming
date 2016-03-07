package team2beat.com.src.DataObjects;

import team2beat.com.src.DataObjects.Lecture;
import team2beat.com.src.DataObjects.Location;

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
	private Location location;
	private Module module;

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

	// Booking Constructor
	public Booking(Time start, Time end, Date theDate, String attListID, Location location, Module module, Lecture lecture)
	{
		this.start = start;
		this.end = end;
		this.theDate = theDate;
		this.attListID = attListID;

		this.locationID = locationID;
		this.staffUsername = staffUsername;
	}

	public Booking(){


	}

	public void addLecture (String classID, String moduleID, Lecture.LectType lType)
	{
		lecture = new Lecture (classID, moduleID, lType);

		this.location = location;
		this.module = module;
		this.lecture = lecture;

	}
}
