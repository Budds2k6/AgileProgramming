package team2beat.com.src.DataObjects;


// DataObject for the student

public class Student extends User

{
	private String studentID;



	//Accessor methods
	public String getStudentID()
	{
		return studentID;
	}

	public String getUserID()
	{
		return userID;
	}


	public Student(String studentID, String username, String firstname, String surname)
	{
		super(username,firstname,surname);

		this.studentID = studentID;
	}

}

