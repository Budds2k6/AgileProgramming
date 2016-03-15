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

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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

            //Bundle detailsBundle = getIntent().getExtras();

            // change this to store the values
            loadModules();

            createClassLabels();

            setLabelText();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    /*
        public void setStaffDetails(Staff staff)
        {
            staffDetails = staff;
        }
    */

    public void setLabelText()
    {
        // display the user details on the screen
        TextView txtName = (TextView) findViewById(R.id.lblLoggedInAs);
        txtName.setText("Logged In As: " + staffDetails.getFirstName() + " " + staffDetails.getSurname());
    }

    public void loadQRCode(View v)
    {
        Intent i = new Intent (getBaseContext(), QrDisplayActivity.class);
        QrDisplayActivity.staffDetails = staffDetails;
        i.putExtra("bookingSelected", 1000);
        StaffMainActivity.this.startActivity(i);
    }


    public void loadModules()
    {
        // call the controller to fetch the dat
        // (store the data in a list / array)
        BookingController bc = new BookingController();
        classes = bc.getTodaysClasses(staffDetails.getStaffID());

    }


    // need to pass a parameter in - the list / array
    public void createClassLabels()
    {
        final ListView moduleList = (ListView) findViewById(R.id.listView);

        ArrayList<String> classDetails = new ArrayList<String>();

        for(int i = 0; i < classes.size(); i++)
        {
            String building = classes.get(i).getBuilding();
            Time startTime = classes.get(i).getStartTime();
            String roomNumber = classes.get(i).getRoomNumber();
            String classType = classes.get(i).getClassType();
            String moduleName = classes.get(i).getModuleName();

            classDetails.add(building + " (" + roomNumber + ")\n" + startTime + "\n" + classType + " (Booking ID = " + classes.get(i).getBookingID() + ")\n" + moduleName);
        }

        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classDetails);

        moduleList.setAdapter(adapter); moduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent(getBaseContext(), QrDisplayActivity.class);
            QrDisplayActivity.staffDetails = staffDetails;
            QrDisplayActivity.theBooking = classes.get(position);
            StaffMainActivity.this.startActivity(i);
        }
    });
    }

    public void loadStaffModules(View v)
    {
        Intent i = new Intent (getBaseContext(), staff_modules.class);

        StaffController sc = new StaffController();
        staff_modules.modules = sc.getAllModules(staffDetails.getStaffID());

        StaffMainActivity.this.startActivity(i);
    }

}
