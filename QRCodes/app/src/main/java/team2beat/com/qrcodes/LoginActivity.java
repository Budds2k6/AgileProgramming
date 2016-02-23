package team2beat.com.qrcodes;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void submitLogin(View v)
    {
        // TODO this function!
        EditText usernameBox = (EditText) findViewById(R.id.editText);
        String username = usernameBox.getText().toString();

        EditText passwordBox = (EditText) findViewById(R.id.editText2);
        String password = usernameBox.getText().toString();

        LoginController lc = new LoginController(username, password);
        boolean success = lc.doLogin();

        Toast toast = Toast.makeText(this, "WAS IT SUCCESSFUL? " + success, Toast.LENGTH_LONG);

        toast.show();

    }

}