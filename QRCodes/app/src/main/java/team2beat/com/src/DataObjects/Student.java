package team2beat.com.src.DataObjects;

<<<<<<< HEAD
// DataObject for the student
public class Student 
=======
public class Student extends User
>>>>>>> 7ca1801b43fc02528031b45f0940ac76d97fd125
{
	private String studentID;

	// Constructor
<<<<<<< HEAD
	Student(){}

	//Accessor methods
	public String getStudentID()
	{
		return studentID;
	}

	public String getUserID()
	{
		return userID;
	}
}
=======
	public Student(String studentID, String username, String password, String firstname, String surname)
	{
		super(username,password,firstname,surname);

		this.studentID = studentID;
	}

}
>>>>>>> 7ca1801b43fc02528031b45f0940ac76d97fd125
