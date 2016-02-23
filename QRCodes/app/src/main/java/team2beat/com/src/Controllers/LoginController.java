package team2beat.com.src.Controllers;

import team2beat.com.src.Models.*;


public class LoginController {
	
	private String username;
	private String password;
	
	public LoginController(String u, String p)
	{
		this.username = u;
		this.password = p;	
	}
	
	public boolean doLogin()
	{
		LoginModel lm = new LoginModel();
		return lm.doLogin(username,password);
	}

}
