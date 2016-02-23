

public class LoginController {
	
private String username;
private String password;

LoginController(String u, String p)
{
	this.username = u;
	this.password = p;	
}

public void doLogin()
{
	LoginModel lm = new LoginModel();
	lm.doLogin(username,password);	
}

}
