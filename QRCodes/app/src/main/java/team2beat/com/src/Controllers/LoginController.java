package team2beat.com.src.Controllers;

import team2beat.com.src.Models.*;


public class LoginController {
	
	private String username;
	private String password;
	private String [] returnData;
	
	public LoginController(String u, String p)
	{
		this.username = u;
		this.password = p;
		returnData = new String [4];
	}

	//change to String [] return
	public boolean doLogin()
	{
		LoginModel lm = new LoginModel(username,password);

		//returnData = lm.theReturns;


		//return lm.doLoginNew(username,password);
		//return lm.onCreate(username,password);
		return true;
		//return returnData;
	}

}
