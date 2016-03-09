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
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;

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
            //List<Class> classes = ...
            loadModules(staffDetails.getStaffID());

            createClassLabels(); // classes

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


    // change the return type to a list / array of the classes
    public void loadModules(String staffData)
    {
        // call the controller to fetch the data
        // (store the data in a list / array)

        // return the list
    }


    // need to pass a parameter in - the list / array
    public void createClassLabels() // List<Class> classes
    {
        // create and add labels to the form/activity
    }

}
