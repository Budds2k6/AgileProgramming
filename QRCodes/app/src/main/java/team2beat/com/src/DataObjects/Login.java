package team2beat.com.src.DataObjects;

import java.util.regex.Pattern;

public class Login
{
	// Class Constructor
	public Login()
	{}
	
	// Perform login
	public void doLogin()
	{	
		// TODO: Gain username && password from somewhere
		String username = "000001";
		String password = "bakedBeans";
		
		if (checkValid(username, password))
		{
			// TODO: Implement successful login
			// Transfer to new section
		}
		else
		{
			// TODO: Login unsuccessful
			// Display error detailing such
		}
	}
	
	// Checks the user has valid access
	public boolean checkValid(String username, String password)
	{	
		if (checkUsername(username) && checkPassword(password))
			{return true;}
		else
		{
			return false;
		}
	}
	
	// Checks the username is within bounds
	private boolean checkUsername(String username)
	{
		if (username.length() <= 0 || username.length() > 10)
		{return false;}
		
		// TODO: Move this to input-control
		// TODO: Either remove, or separate for students / staff
		if (Pattern.matches("[a-zA-Z]+", username) == false)
		{return false;}
		
		return true;
	}
	
	// Checks the password is within bounds
	private boolean checkPassword(String password)
	{
		if (password.length() <=0 || password.length() > 45)
		{ return false; }

		return true;
	}

}
