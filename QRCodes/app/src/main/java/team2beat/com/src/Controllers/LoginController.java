package team2beat.com.src.Controllers;

import team2beat.com.src.Models.*;


public class LoginController {

    private String username;
    private String password;


    public LoginController(String u, String p) {
        this.username = u;
        this.password = p;
        //this.returnData = new String [4];
    }

    //change to String [] return
    public String [] doLogin() {
        LoginModel lm = new LoginModel(username, password);
        return lm.doLogin();

    }

}
