package team2beat.com.src.Controllers;

import team2beat.com.src.Models.*;


public class LoginController {

    private String username;
    private String password;
    private String[] returnData;

    public LoginController(String u, String p) {
        this.username = u;
        this.password = p;
        //this.returnData = new String [4];
    }

    //change to String [] return
    public String[] doLogin() {
        LoginModel lm = new LoginModel(username, password);

        while (returnData == null) {
            returnData = lm.theReturns;
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
