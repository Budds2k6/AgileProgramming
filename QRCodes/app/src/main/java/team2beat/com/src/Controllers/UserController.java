package team2beat.com.src.Controllers;
import team2beat.com.src.Models.*;

public class UserController
{
	public void CreateUser ()
	{
		if (!loginCheck())
		{
			// TODO: Display error message
			return;
		}
		
		// Establish model
		UserModel userModel = new UserModel();
		
		// Extract user details
		// TODO: Gain user details from form
		String username = "";
		String password = "";
		String firstname = "";
		String surname = "";
		int userType = -1;
		
		switch (userType)
		{
			case 1: // Add Student
				break;
			case 2: // Add staff
				break;
			default: // Return without action
				break;
		}
		
		// Creates the new user
		UserModel newUser = new UserModel(username, password, firstname, surname);
		
		// Adds the user to the database, and recovers the ID
		String userID = userModel.createUser (newUser);
		
		// TODO: Adding of user complete
	}
	
	// Creates student information
	private String CreateStudent()
	{
		return "";
	}
	
	private boolean loginCheck ()
	{
		// TODO: Check user is logged in
		return true;
	}
}