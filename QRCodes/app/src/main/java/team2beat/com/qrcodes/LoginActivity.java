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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void submitLogin(View v) {
        // TODO this function!
    try {
        EditText usernameBox = (EditText) findViewById(R.id.editText);
        String username = usernameBox.getText().toString();

        EditText passwordBox = (EditText) findViewById(R.id.editText2);
        String password = passwordBox.getText().toString();

        LoginController lc = new LoginController(username, password);
        String[] successDetails = lc.doLogin();
        //comment


        Toast toast;

        if (successDetails[0] != null) {
            if (!successDetails[0].equals("Login Failed")) {
                toast = Toast.makeText(this, "Successfully Logged In as " + successDetails[2] + successDetails[3], Toast.LENGTH_LONG);
            } else {
                toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
            }

        } else {
            toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
        }

        toast.show();
    }catch(Exception e){
        Toast toast = Toast.makeText(this, "LOGIN FAILED...", Toast.LENGTH_LONG);
        toast.show();
        e.printStackTrace();

    }
    }

    public void dummyLogin(View v) {
        Intent i = new Intent(getBaseContext(), StaffMainActivity.class);
        LoginActivity.this.startActivity(i);
    }

    public void dummyLoginStudent(View v) {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        LoginActivity.this.startActivity(i);
    }

}
