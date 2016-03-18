package team2beat.com.qrcodes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import team2beat.com.src.Controllers.BookingController;
import team2beat.com.src.Controllers.StaffController;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Staff;

public class StaffMainActivity extends AppCompatActivity {

    public static Staff staffDetails;
    ArrayList<Booking> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_staff_main);

            // change this to store the values
            loadModules();

            // display details on the screen
            createClassLabels();
            setLabelText();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void setLabelText()
    {
        // display the user details on the screen
        TextView txtName = (TextView) findViewById(R.id.lblLoggedInAs);
        txtName.setText("Logged In As: " + staffDetails.getFirstName() + " " + staffDetails.getSurname());
    }

    @Override
    public void onBackPressed() {
    }

    public void loadModules()
    {
        // call the controller to fetch the dat
        // (store the data in a list / array)
        BookingController bc = new BookingController();
        classes = bc.getTodaysClasses(staffDetails.getStaffID());
    }


    public void refresh(View v)
    {
        Intent i = new Intent(getBaseContext(), StaffMainActivity.class);
        finish();
        StaffMainActivity.this.startActivity(i);
    }

    // need to pass a parameter in - the list / array
    public void createClassLabels()
    {
        final ListView moduleList = (ListView) findViewById(R.id.listView);

        ArrayList<String> classDetails = new ArrayList<String>();

        java.util.Date currDate = new java.util.Date();

        //Gets current time
        Calendar c = Calendar.getInstance();
        c.setTime(currDate);


        for(int i = 0; i < classes.size(); i++)
        {
            String building = classes.get(i).getBuilding();
            Time startTime = classes.get(i).getStartTime();
            String roomNumber = classes.get(i).getRoomNumber();
            String classType = classes.get(i).getClassType();
            String moduleName = classes.get(i).getModuleName();

            String output = "";


            // checks if happening NOW
            // get start time
            Calendar c2 = Calendar.getInstance();
            c2.setTime(classes.get(i).getStartTime());


            Calendar c3 = Calendar.getInstance();
            c3.setTime(classes.get(i).getStartTime());

            c3.add(Calendar.HOUR_OF_DAY, 1);

            // if the current time is after the start time but before the end time, the class is happening right now
            if(classes.get(i).getEndTime() != null)
            {
                output += "[ENDED] ";
            }else if(c.after(c2) && c.before(c3))
            {
                output += "[RIGHT NOW] ";
            }else
            {
                output += "[] ";
            }


            // display the output
            output += (building + " (" + roomNumber + ")\n" + startTime + "\n" + moduleName + " (" + classType + ")");

            classDetails.add(output);

        }

        // create the list items
        final customAdapter adapter = new customAdapter(this, classDetails.toArray(new String[0]));
        moduleList.setTag(1);
        moduleList.setAdapter(adapter);
        moduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), QrDisplayActivity.class);
                QrDisplayActivity.staffDetails = staffDetails;
                QrDisplayActivity.theBooking = classes.get(position);
                StaffMainActivity.this.startActivity(i);
                return;
            }
        });
    }

    public void loadStaffModules(View v)
    {

        // load the page for the staff modules (ALL)
        Intent i = new Intent (getBaseContext(), staff_modules.class);

        StaffController sc = new StaffController();

        staff_modules.modules = sc.getAllModules(staffDetails.getStaffID(), staffDetails.getAccessLevel());
        staff_modules.loggedInStaff = staffDetails.getStaffID();

        StaffMainActivity.this.startActivity(i);
    }

}
