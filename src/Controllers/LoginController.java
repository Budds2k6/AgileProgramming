// Controls the login for the application
public class LoginController 
{
	private String username;
	private String password;

	// Constructor
	LoginController (String username, String password)
	{
		this.username = username;
		this.password = password;	
	}
	
	// Performs the login
	public void doLogin()
	{
		LoginModel loginModel = new LoginModel();
		loginModel.doLogin(username, password);	
	}
}
