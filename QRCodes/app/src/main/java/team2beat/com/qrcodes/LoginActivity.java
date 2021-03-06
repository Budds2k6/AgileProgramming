package team2beat.com.qrcodes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import team2beat.com.src.AsyncClasses.ConnectionCheck;
import team2beat.com.src.Controllers.LoginController;
import team2beat.com.src.DataObjects.Staff;
import team2beat.com.src.DataObjects.Student;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void submitLogin(View v) {

        // check that there is a connection to the server available
        if (isConnectionAvailable()) {

            try {

                // extract the username and password from the UI
                EditText usernameBox = (EditText) findViewById(R.id.editText);
                String username = usernameBox.getText().toString();
                username = username.trim();

                EditText passwordBox = (EditText) findViewById(R.id.editText2);
                String password = passwordBox.getText().toString();

                // attempt to log in
                LoginController lc = new LoginController(username, password);
                String[] successDetails = lc.doLogin();

                Toast toast;

                // check for nulls - ERROR HANDLING
                if (successDetails != null && successDetails[0] != null) {

                    // the the login succeeded...
                    if (!successDetails[0].equals("Login Failed")) {

                        // get the values needed
                        toast = Toast.makeText(this, "Successfully Logged In as " + successDetails[1] + " " + successDetails[2], Toast.LENGTH_LONG);

                        // check if staff or student. Create an object of the relevant type
                        if (successDetails[4].equals("staff")) {

                            String staffID = successDetails[3];

                            Staff theStaff = new Staff(staffID, successDetails[0], successDetails[1], successDetails[2]);
                            theStaff.setJob(Integer.valueOf(successDetails[5]), Integer.valueOf(successDetails[6]));

                            // login in as staff
                            dummyLogin(v, theStaff);

                        } else if (successDetails[4].equals("student")) {      // password = "pass"

                            String studentID = successDetails[3];

                            Student theStudent = new Student(studentID, successDetails[0], successDetails[1], successDetails[2]);

                            // login as student
                            dummyLoginStudent(v, theStudent);

                        }
                    } else {
                        toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
                    }

                } else {
                    toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
                }

                // show the message to tell the user if the login was successful or not
                TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                toastMessage.setBackgroundColor(Color.TRANSPARENT);

                toast.show();

            } catch (Exception e) {

                // pop up message - pop up... toast... get it? Clever Android...
                Toast toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
                TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                toastMessage.setBackgroundColor(Color.TRANSPARENT);
                toast.show();
                e.printStackTrace();
            }
        }
        else
        {
            // No connection available
            Toast toast = Toast.makeText(this, "Connection Timed Out", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void dummyLogin(View v, Staff theStaff) {
        Intent i = new Intent(getBaseContext(), StaffMainActivity.class);

        // the staff members details need to be passed
        StaffMainActivity.staffDetails = theStaff;
        LoginActivity.this.startActivity(i);
    }

    public void dummyLoginStudent(View v, Student theStudent) {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        // static variable to store the staff details
        MainActivity.studentDetails = theStudent;
        LoginActivity.this.startActivity(i);
    }

    public boolean isConnectionAvailable()
    {
        // create an instance of the class to check if a connection is available
        ConnectionCheck cc = new ConnectionCheck();

        // start the checking of the connection in a new thread
        Thread newThread = new Thread(cc);
        newThread.start();


        //join to the main thread
        try {
            Thread.sleep(4000);
            newThread.join();
        }catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        // get whether or not the connection is available
        return cc.checkConnection();
    }

}
