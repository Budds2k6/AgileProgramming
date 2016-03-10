package team2beat.com.src.DataObjects;

import android.os.Parcel;
import android.os.Parcelable;

import team2beat.com.src.DataObjects.Lecture;
import team2beat.com.src.DataObjects.Location;

import java.sql.Date;
import java.sql.Time;

public class Booking implements Parcelable
{
	private String moduleID;
	// Booking details
	private String bookingID;
	private String classID;
	private Time start;
	private Time end;
	private Date theDate;
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
	public Booking (String bookingID, String classID, Time start, Time end,Date theDate, String locationID, String staffID, String attListID, String moduleID, String className, String classType, String roomNum, String building)
	{
		this.bookingID = bookingID;
		this.classID = classID;
		this.start = start;
		this.end = end;
		this.theDate = theDate;
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
	public Booking(Time start, Time end, Date theDate, String attListID, Location location, Module module, Lecture lecture)
	{
		this.start = start;
		this.end = end;
		//this.theDate = theDate;
		this.attListID = attListID;

		this.locationID = locationID;
		this.staffID = staffID;
	}

	public Booking(){}

	public String getBuilding() { return this.building; }
	public String getRoomNumber() { return this.roomNumber; }
	public Time getStartTime() { return this.start; }
	public String getClassType() { return this.classType; }
	public String getBookingID() { return this.bookingID; }

	public void addLecture (String classID, String moduleID, Lecture.LectType lType)
	{
		lecture = new Lecture (classID, moduleID, lType);

		this.location = location;
		this.module = module;
		this.lecture = lecture;

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(0);
	}


}
