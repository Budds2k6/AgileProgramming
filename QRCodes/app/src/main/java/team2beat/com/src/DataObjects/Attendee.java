package team2beat.com.src.DataObjects;

public class Attendee 
{
	String studentName;
	String userID;
	String attListID;
	String reason;
	boolean attended;
	
	// Constructor
	public Attendee (String student, String uID, String attID, String r, boolean att)
	{
		this.studentName = student;
		this.userID = uID;
		this.attListID = attID;
		this.reason = r;
		this.attended = att;
	}

	public String getStudentName() { return studentName; }
}
