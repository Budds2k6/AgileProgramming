package team2beat.com.src.Models;

import team2beat.com.src.AsyncClasses.*;

public class LoginModel{

	private String username;
	private String password;
	private String[] returnData;

	// Class Constructor
	public LoginModel(String u, String p)
	{
		this.username = u;
		this.password = p;

	}


	public String [] doLogin(){

		LoginAsync la = new LoginAsync(username, password);


		while (returnData == null) {
			returnData = la.toReturn;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (returnData[0] != null) {
			if (returnData[0].equals("Login Failed")) {

				return null;

			}
		}
		//return lm.doLoginNew(username,password);
		//return lm.onCreate(username,password);
		//return true;
		return returnData;


	}


}
