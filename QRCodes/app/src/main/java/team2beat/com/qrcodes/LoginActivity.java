package team2beat.com.qrcodes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        try {

            // extract the username and password from the UI
            EditText usernameBox = (EditText) findViewById(R.id.editText);
            String username = usernameBox.getText().toString();

            EditText passwordBox = (EditText) findViewById(R.id.editText2);
            String password = passwordBox.getText().toString();

            // attempt to log in
            LoginController lc = new LoginController(username, password);
            String[] successDetails = lc.doLogin();

            Toast toast;

            if (successDetails[0] != null) {
                if (!successDetails[0].equals("Login Failed")) {
                    toast = Toast.makeText(this, "Successfully Logged In as " + successDetails[2] + successDetails[3], Toast.LENGTH_LONG);
                    if (successDetails[0].equals("AECobley")) {

                        String StaffID = "SSE1325";
                        String JobID = "2751";

                        Staff theStaff = new Staff(StaffID, JobID, successDetails[0], successDetails[1], successDetails[2], successDetails[3]);


                        dummyLogin(v, theStaff);
                    } else if (successDetails[0].equals("FClyne")) {      // password = "pass"

                        String StudentID = "120005432";

                        Student theStudent = new Student(StudentID, successDetails[0], successDetails[1], successDetails[2], successDetails[3]);

                        dummyLoginStudent(v, theStudent);

                    }
                } else {
                    toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
                }

            } else {
                toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
            }

            toast.show();
        } catch (Exception e) {

            // pop up message - pop up... toast... get it? Clever Android...
            Toast toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
            toast.show();
            e.printStackTrace();

        }
    }

    public void dummyLogin(View v, Staff theStaff) {
        Intent i = new Intent(getBaseContext(), StaffMainActivity.class);

        // the staff members details need to be passed
        i.putExtra("details", theStaff);
        LoginActivity.this.startActivity(i);
    }

    public void dummyLoginStudent(View v, Student theStudent) {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        // the students details need to be passed
        i.putExtra("details", theStudent);
        LoginActivity.this.startActivity(i);
    }

}
