package team2beat.com.src.DataObjects;

import team2beat.com.src.DataObjects.Lecture;
import team2beat.com.src.DataObjects.Location;

import java.sql.Date;
import java.sql.Time;

public class Booking
{
	private String moduleID;
	// Booking details
	private String bookingID;
	private String classID;
	private Date start;
	private Date end;
	//private Date theDate;
	private String attListID;
	private String locationID;
	private String staffID;
	private String className;
	private String classType;
	private String roomNumber;
	private String building;

	private Lecture lecture;
	private Location location;
	private Module module;

	// Constructor
	public Booking (String bookingID, String classID, Date start, Date end, String locationID, String staffID, String attListID, String moduleID, String className, String classType, String roomNum, String building)
	{
		this.bookingID = bookingID;
		this.classID = classID;
		this.start = start;
		this.end = end;
		this.locationID = locationID;
		this.staffID = staffID;
		this.attListID = attListID;
		this.moduleID = moduleID;
		this.className = className;
		this.classType = classType;
		this.roomNumber = roomNum;
		this.building = building;

	}

	// Booking Constructor
	public Booking(Date start, Date end, Date theDate, String attListID, Location location, Module module, Lecture lecture)
	{
		this.start = start;
		this.end = end;
		//this.theDate = theDate;
		this.attListID = attListID;

		this.locationID = locationID;
		this.staffID = staffID;
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
