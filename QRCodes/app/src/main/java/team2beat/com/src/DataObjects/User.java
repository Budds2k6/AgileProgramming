// DataObject for the user
public class User
{
	String userID;
	String password;
	String firstname;
	String surname;

	// Constructor
	public User(String username, String password, String firstname, String surname)
	{
		this.userID = username;
		this.password = password;
		this.firstname = firstname;
		this.surname = surname;
	}

	//Accessor methods
	public String getUsername()
	{
		return userID;
	}

	public String getPassword()
	{
		return password;
	}

	public String getFirstName()
	{
		return firstname;
	}

	public String getSurname()
	{
		return surname;
	}
}
