package team2beat.com.qrcodes;

import android.content.Intent;
import android.os.Debug;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

import team2beat.com.src.DataObjects.Booking;

public class ClassDetails extends AppCompatActivity {


    public static Booking theBooking;

    TextView module, room, location, time, type, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        // find the text components of the activity
        module = (TextView) findViewById(R.id.textLecture);
        room = (TextView) findViewById(R.id.textRoom);
        location = (TextView) findViewById(R.id.textLocation);
        date = (TextView) findViewById(R.id.textDate);
        type = (TextView) findViewById(R.id.textType);
        time = (TextView) findViewById(R.id.textTime);

        // set the text to the correct values
        module.setText(theBooking.getClassName());
        room.setText("Room No: " + theBooking.getRoomNumber());
        location.setText("Building: " + theBooking.getBuilding());
        date.setText("Date: " + theBooking.getDate().toString());
        time.setText("Time: " + theBooking.getStartTime().toString());

        String lectureType = theBooking.getClassType();
        if (lectureType.contains("Lec"))
        {
            lectureType = "Lecture";
        }
        else if (lectureType.contains("Tut"))
        {
            lectureType = "Tutorial";
        }
        else if (lectureType.contains("Sem"))
        {
            lectureType = "Seminar";
        }

        type.setText("Class Type: " + lectureType);
    }

    public void goBack(View v)
    {
        // go back to the module statistics page
        Intent i = new Intent (getBaseContext(), module_statistics.class);
        ClassDetails.this.startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void viewAttendance(View v)
    {
        // set the variables for the Register page
        RegisterView.attendanceListID = Integer.valueOf(theBooking.getAttListID());
        RegisterView.bookingID = Integer.valueOf(theBooking.getBookingID());
        RegisterView.isLive = false;

        // load the page
        Intent i = new Intent(getBaseContext(), RegisterView.class);
        ClassDetails.this.startActivity(i);
    }

    @Override
    public void onBackPressed() {
    }

    public void displayToast()
    {
        // custom toast
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toastLayout));
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.show();
    }
}
