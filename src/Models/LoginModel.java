import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login
{
	// Class Constructor
	public Login()
	{}
	

	// Perform login
	public boolean doLogin(String username, String password)
	{	
		try{
		// TODO: Gain username && password from somewhere
		//username = "000001";
		//password = "bakedBeans";
		
		String theDriver = "com.mysql.jdbc.Driver";  
		Class driver_class = Class.forName(theDriver);		   
		Driver driver = (Driver) driver_class.newInstance();
		DriverManager.registerDriver(driver);
	    Connection conn = DriverManager.getConnection("jdbc:mysql://silva.computing.dundee.ac.uk:3306/15agileteam2db?" + "user=15agileteam2&password=349.at2.psswd");         
	    PreparedStatement ps = conn.prepareStatement("Select * from user where username=? and password=?");
	    ps.setString(1, username); 
	    ps.setString(2, password);
	    ResultSet rs = ps.executeQuery();
	    if(rs.next())  {                      
	    	   String returnedUser = rs.getString("username");
	           String returnedPwd = rs.getString("password");
	           
	           String firstname = rs.getString("firstname");
	           String surname = rs.getString("surname");
	           
	           System.out.println(returnedUser + "  |  " + returnedPwd + "  |  " + theDatas);
	           return true;	    
	    }
	       else{
	    	   writer.println("FAILED | "+ username + " | " + password);
			   writer.close();
			   return false;}

	       
	   }
	   catch(Exception e){
	       e.printStackTrace();
	       return false;
	   }
	    
	    
		
		
		
		//if (checkValid(username, password))
		//{
			// TODO: Implement successful login
			// Transfer to new section
		//}
		//else if
		//{
			// TODO: Login unsuccessful
			// Display error detailing such
		//}
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
