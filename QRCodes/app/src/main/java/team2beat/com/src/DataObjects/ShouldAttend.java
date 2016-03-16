package team2beat.com.src.DataObjects;

public class ShouldAttend
{
	String studentName;
	String userID;

	// Constructor
	public ShouldAttend(String student, String uID)
	{
		this.studentName = student;
		this.userID = uID;
	}

	public String getStudentName() { return studentName; }
	public String getUserID() { return userID; }
}
