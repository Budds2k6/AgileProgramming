// DataObject for the user
package team2beat.com.src.DataObjects;

import java.io.Serializable;

public class User implements Serializable {
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
