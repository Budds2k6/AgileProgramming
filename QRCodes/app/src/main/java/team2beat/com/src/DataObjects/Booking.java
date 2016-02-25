import java.sql.Date;
import java.sql.Time;


public class Booking 
{
	private String bookingID;
	private String classID;
	// Date start;
	private Time start;
	private Time end;
	private Date theDate;
	private String attListID;
	private String locationID;
	private String staffUsername;

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
}
