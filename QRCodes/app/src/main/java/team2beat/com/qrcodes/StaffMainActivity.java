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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import java.util.List;

import team2beat.com.src.Controllers.BookingController;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Staff;

public class StaffMainActivity extends AppCompatActivity {

    Staff staffDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_staff_main);

            Bundle detailsBundle = getIntent().getExtras();

            // get the details which were passed through
            staffDetails = (Staff) detailsBundle.getSerializable("details");

            // change this to store the values
            ArrayList<Booking> classes = loadModules();

            createClassLabels(classes);

            setLabelText();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void setStaffDetails(Staff staff)
    {
        staffDetails = staff;
    }

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
        StaffMainActivity.this.startActivity(i);
    }


    public ArrayList<Booking> loadModules()
    {
        // call the controller to fetch the dat
        // (store the data in a list / array)
        BookingController bc = new BookingController();
        ArrayList<Booking> classes = bc.getTodaysClasses(staffDetails.getStaffID());

        // return the list
        return classes;
    }


    // need to pass a parameter in - the list / array
    public void createClassLabels(List<Booking> classes)
    {
        final ListView moduleList = (ListView) findViewById(R.id.listView);
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classes);
    }

    public void loadStaffModules(View v)
    {
        Intent i = new Intent (getBaseContext(), staff_modules.class);
        StaffMainActivity.this.startActivity(i);
    }

}
