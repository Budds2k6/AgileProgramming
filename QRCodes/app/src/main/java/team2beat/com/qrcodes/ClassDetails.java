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

    TextView module, title, room, location, date, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        module = (TextView) findViewById(R.id.textLecture);
        title = (TextView) findViewById(R.id.textTitle);
        room = (TextView) findViewById(R.id.textRoom);
        location = (TextView) findViewById(R.id.textLocation);
        date = (TextView) findViewById(R.id.textDate);

        module.setText(theBooking.getClassName());
        room.setText("Room No.: " + theBooking.getRoomNumber());
        location.setText("Building: " + theBooking.getBuilding());
        date.setText("Date: " + theBooking.getStartTime().toString());
        type.setText("Class Type: " + theBooking.getClassType());
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
        RegisterView.attendanceListID = Integer.valueOf(theBooking.getAttListID());
        RegisterView.bookingID = Integer.valueOf(theBooking.getBookingID());
        RegisterView.isLive = false;

        Intent i = new Intent(getBaseContext(), RegisterView.class);
        //i.putExtra("attendanceID", theBooking.getAttListID());
        //i.putExtra("bookingID", theBooking.getBookingID());
        ClassDetails.this.startActivity(i);
    }

    public void displayToast()
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toastLayout));
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.show();
    }
}
