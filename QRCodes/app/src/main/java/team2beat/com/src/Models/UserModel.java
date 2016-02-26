package team2beat.com.src.Models;

public class UserModel
{

	String username;
	String password;
	String firstName;
	String surname;


	// Constructor
	public UserModel()
	{
		// TODO: Add connection string
	}

	// Constructor
	public UserModel(String u, String p, String f, String s)
	{
		// TODO: Add connection string
		username = u;
		password = p;
		firstName = f;
		surname = s;
	}

	
	// Creates a new user
	public String createUser (UserModel thisUser)
	{
		return "1";
	}
	
	// Edits a user
	public void editUser (UserModel thisUser)
	{
		
	}
	
	// Deletes the user
	public void deleteUser (UserModel userID)
	{
		
	}
	
	// Finds the user
	public void findUser (UserModel userID)
	{
		
	}

}
