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
		string username = "000001";
		string password = "bakedBeans";
		
		if (checkValid(username, password))
		{
			// TODO: Implement successful login
			// Transfer to new section
		}
		else if
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
	private bool checkUsername(string username)
	{
		if (username.Length <= 0 || username.Length > 10)
		{return false;}
		
		// TODO: Move this to input-control
		// TODO: Either remove, or separate for students / staff
		if (Pattern.matches("[a-zA-Z]+", username) == false)
		{return false;}
		
		return true;
	}
	
	// Checks the password is within bounds
	private bool checkPassword(string password)
	{
		if (password.Length <=0 || password > 45)
		{ return false; }		
	}

}
