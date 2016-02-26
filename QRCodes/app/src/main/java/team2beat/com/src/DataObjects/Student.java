package team2beat.com.src.DataObjects;

public class Student extends User
{
	private String studentID;

	// Constructor
	public Student(String studentID, String username, String password, String firstname, String surname)
	{
		super(username,password,firstname,surname);

		this.studentID = studentID;
	}

}