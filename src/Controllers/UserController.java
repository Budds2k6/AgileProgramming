public class UserController
{
	public void CreateUser ()
	{
		if (!LoginCheck)
		{
			// TODO: Display error message
			return;
		}
		
		// Establish model
		UserModel userModel = new UserModel();
		
		// Extract user details
		// TODO: Gain user details from form
		String username; 
		String password;
		String firstname;
		String surname
		int userType;
		
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
		User newUser = new User(username, password, firstname, surname);
		
		// Adds the user to the database, and recovers the ID
		String userID = userModel.createUser (newUser);
		
		// TODO: Adding of user complete
	}
	
	// Creates student information
	private String CreateStudent ()
	{
		
	}
	
	private boolean LoginCheck ()
	{
		// TODO: Check user is logged in
		return true;
	}
}