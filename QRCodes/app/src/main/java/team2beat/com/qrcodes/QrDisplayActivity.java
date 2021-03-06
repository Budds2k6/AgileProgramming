package team2beat.com.qrcodes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import team2beat.com.src.Controllers.BookingController;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Module;
import team2beat.com.src.DataObjects.Staff;

public class QrDisplayActivity extends AppCompatActivity {


    BookingController bookingController;
    static Staff staffDetails;
    static Booking theBooking;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_display);


        bookingController = new BookingController(theBooking.getBookingID());

        // set up the labels
		setClassLabels();

        // if the class is stll active (i.e. not ended), show the QR code
        if(theBooking.getEndTime() == null){
            // create a QR Code, representing the attendance list ID
            generateQRCode(theBooking.getBookingID());
        }
        //if it has ended, inform the user
        else
        {
            Toast toast = Toast.makeText(this, "Please Note: This class has ended", Toast.LENGTH_LONG);

            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setBackgroundColor(Color.TRANSPARENT);

            toast.show();
        }

    }

    @Override
    public void onBackPressed() {
    }

    public void setClassLabels()
    {
        // display details on the screen
        TextView txtModID = (TextView) findViewById(R.id.lblModuleID);
        String classID = "Module: " + theBooking.getModuleID();
        txtModID.setText(classID);

        TextView txtStartTime = (TextView) findViewById(R.id.lblStartTime);
        txtStartTime.setText(String.valueOf(theBooking.getStartTime()));
    }

    public void BackToRegister(View v)
    {
        // go the register page
        RegisterView.attendanceListID = Integer.valueOf(theBooking.getAttListID());
        RegisterView.bookingID = Integer.valueOf(theBooking.getBookingID());
        RegisterView.isLive = true;

        // show the register
        Intent i = new Intent(getBaseContext(), RegisterView.class);
        QrDisplayActivity.this.startActivity(i);
    }

    public void backToStaffMenu(View v)
    {
        // show the home page - passing the correct details
        Intent i = new Intent(getBaseContext(), StaffMainActivity.class);
        StaffMainActivity.staffDetails = staffDetails;
        QrDisplayActivity.this.startActivity(i);
    }

    public void endClass(View v)
    {
        // end the class
        bookingController.endClass();
        StaffMainActivity.staffDetails = staffDetails;
        backToStaffMenu(v);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qr_display, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_qr_display, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }



    // code modified / inspiration taken from:
    // http://crunchify.com/java-simple-qr-code-generator-example/

    public void generateQRCode(String attendanceListID) {

        // Create a QR Code Writer
        QRCodeWriter qrWriter = new QRCodeWriter();

        int width = 300;
        int height = 300;

        try
        {
            // create the 2d array of bits - from the content
            BitMatrix bitMatrix = qrWriter.encode(attendanceListID, BarcodeFormat.QR_CODE, width, height);
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            // loop through all pixels and set the colour to the appropriate colour (basede on the bool)
            for(int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {
                    // if true, he colour is black, otherwise, it is white. This wil lmake up the QR Code
                    if (bitMatrix.get(i, j) == true)
                    {
                        bitmap.setPixel(i, j, Color.BLACK);
                    }else
                    {
                        bitmap.setPixel(i, j, Color.WHITE);
                    }
                }
            }

            // set image on form
            ImageView theImage = (ImageView) this.findViewById(R.id.qrCode);
            theImage.setImageBitmap(bitmap);

        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }

    }
}
