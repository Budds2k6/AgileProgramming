package team2beat.com.qrcodes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.regex.Pattern;

import team2beat.com.src.Controllers.AttendeeListController;
import team2beat.com.src.Controllers.BookingController;
import team2beat.com.src.DataObjects.Attendee;
import team2beat.com.src.DataObjects.ShouldAttend;
import team2beat.com.src.DataObjects.Student;
import team2beat.com.src.Models.CustomComparator;

public class RegisterView extends AppCompatActivity {

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
    public static int attendanceListID;
    public static int bookingID;

    ArrayList<ShouldAttend> allStudents = new ArrayList<>();
    ArrayList<Boolean> studentStatus = new ArrayList<>();

    public static boolean isLive = false;

    ArrayList<ShouldAttend> shouldAttend;
    ArrayList<Attendee> attendees;
    ListView moduleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        //Intent i = this.getIntent();

        //Bundle detailBundle = i.getExtras();

        //attendanceListID = Integer.valueOf(detailBundle.getString("attendanceID"));
        //bookingID = Integer.valueOf(detailBundle.getString("bookingID"));

        AttendeeListController alc = new AttendeeListController();

        // people who have signed in
        attendees = alc.getAttendanceListByID(attendanceListID);

        // people who should attend
        shouldAttend = alc.getShouldAttend(bookingID);
        //ArrayList<ShouldAttend> shouldAttend = new ArrayList<ShouldAttend>();

        createStudentLabels(attendees, shouldAttend);

    }

    @Override
    protected void onResume(){
        super.onResume();
	//setLabelColors();
	}


    public void createStudentLabels(ArrayList<Attendee> attendees, ArrayList<ShouldAttend> shouldAttend)
    {

        ArrayList<Boolean> isOnList = new ArrayList<Boolean>();

        // convert attendees to the new format
        ArrayList<ShouldAttend> whoHasSignedIn = new ArrayList<ShouldAttend>();


        // Convert Student objects into the required type
        for(int i = 0; i < attendees.size(); i++)
        {
            ShouldAttend newSA = new ShouldAttend(attendees.get(i).getStudentName(), attendees.get(i).getUserID());
            whoHasSignedIn.add(newSA);
        }

        for(int i = 0; i < whoHasSignedIn.size(); i++)
        {
            isOnList.add(false);
        }


        // remove the students who have attended from the list of students who SHOULD attend
        for(int i = 0; i < shouldAttend.size(); i++)
        {
            String userID = shouldAttend.get(i).getUserID();

            for(int j = 0; j < whoHasSignedIn.size(); j++)
            {
                if(userID.equals(whoHasSignedIn.get(j).getUserID()))
                {
                    isOnList.set(j, true);
                    shouldAttend.remove(i);
                    i--;

                    break;
                }
            }
        }


        ArrayList<ShouldAttend> signedInButNotOnList = new ArrayList<ShouldAttend>();

        // work out which students have signed in but are not 'meant to' be there
        for(int i = 0; i < whoHasSignedIn.size(); i++)
        {
            if(!isOnList.get(i))
            {
                signedInButNotOnList.add(whoHasSignedIn.get(i));
                isOnList.remove(i);
                whoHasSignedIn.remove(i);
                i--;
            }
        }


        moduleList = (ListView) findViewById(R.id.listStudents);

        ArrayList<String> studentDetails = new ArrayList<String>();


        // sort the lists into alphabetical order
        try {
            Collections.sort(signedInButNotOnList, new CustomComparator());
            Collections.sort(whoHasSignedIn, new CustomComparator());
            Collections.sort(shouldAttend, new CustomComparator());
        }catch(Exception e)
        {
            e.printStackTrace();
            e.printStackTrace();
        }

        // output with formatting to show who is there and who is not
        for(int i = 0; i < signedInButNotOnList.size(); i++)
        {
            studentDetails.add("[Not on Register] " + signedInButNotOnList.get(i).getStudentName());
            allStudents.add(signedInButNotOnList.get(i));
            studentStatus.add(true);
        }
        for(int i = 0; i < whoHasSignedIn.size(); i++)
        {
            studentDetails.add("[] " + whoHasSignedIn.get(i).getStudentName());
            allStudents.add(whoHasSignedIn.get(i));
            studentStatus.add(true);
        }
        for(int i = 0; i < shouldAttend.size(); i++)
        {
            studentDetails.add("[Not Present] " + shouldAttend.get(i).getStudentName());
            allStudents.add(shouldAttend.get(i));
            studentStatus.add(false);
        }

        // set the values of the module list
        final customAdapter adapter = new customAdapter(this, studentDetails.toArray(new String[0]));
        moduleList.setTag(0);
        moduleList.setAdapter(adapter);

        // add a click listener to the student items in the list
        moduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 confirmAdd(position);
            }
        });


        // calculate the percentage for the class attendance, and display on the screen
        String percentage = calculateAttendancePercentage(whoHasSignedIn.size(), shouldAttend.size(), signedInButNotOnList.size());

        TextView txtAttendance = (TextView) findViewById(R.id.textView2);
        txtAttendance.setText(percentage);
    }


    void confirmAdd(final int pos)
    {
        // create a pop up message to ask the user to confirm or cancel their choice
        boolean signedIn = studentStatus.get(pos);

        // check that the student has not already been signed in
        if(!signedIn) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // ask for confirmation
            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you wish to sign " + allStudents.get(pos).getStudentName() + " into this class?");

            // Set up the buttons
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ShouldAttend s = allStudents.get(pos);
                    PresentRecord pr = new PresentRecord(bookingID, s.getUserID());
                    manuallySignInStudent(pr);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return;
                }
            });

            // create the alert and change the button colours
            AlertDialog alert = builder.create();
            alert.show();
            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setBackgroundColor(Color.argb(255, 150, 0, 0));
            Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            pbutton.setBackgroundColor(Color.argb(255, 0, 150, 0));

        }else
        {
            // if the student has already been signed in, tell the user
            Toast toast = Toast.makeText(getBaseContext(), allStudents.get(pos).getStudentName() + " has already been signed in!", Toast.LENGTH_LONG);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setBackgroundColor(Color.TRANSPARENT);
            toast.show();
        }
    }

    @Override
    public void onBackPressed() {
    }


    String calculateAttendancePercentage(int attended, int missing, int extra)
    {

        // calculate the attendance for the class
        int total = attended + missing;

        float percent = ((float)attended / (float)total) * 100;

        DecimalFormat df = new DecimalFormat("#.#");

        String returnVal = "Attendance: " + df.format(percent) + "%";

        if(extra > 0)
        {
            returnVal += " + " + extra + " students who are here unexpectedly";
        }

        return returnVal;
    }


    public void refresh(View v)
    {
        // refresh the same page
        Intent i = new Intent(getBaseContext(), RegisterView.class);
        finish();
        RegisterView.this.startActivity(i);
    }

    public void BackToQR(View v)
    {
        // navigate to the correct page
        if(isLive)
        {
            Intent i = new Intent(getBaseContext(), QrDisplayActivity.class);
            RegisterView.this.startActivity(i);
        }else
        {
            Intent i = new Intent(getBaseContext(), ClassDetails.class);
            RegisterView.this.startActivity(i);
        }
    }


    String inputID = "";
    //http://stackoverflow.com/questions/10903754/input-text-dialog-android
    public void addStudentByID(View v)
    {
        // give the user an option of which student to add - an input box in which they can type the students ID
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Student ID");

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputID = input.getText().toString();

                // call the controller
                PresentRecord pr = new PresentRecord(bookingID, inputID);

                manuallySignInStudent(pr);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    boolean manuallySignInStudent(PresentRecord pr)
    {
        BookingController bc = new BookingController();
        boolean success = bc.setAttendance(pr);

        // if errors, tell the user
        if(!success)
        {
            Toast toast = Toast.makeText(getBaseContext(), "ERROR. Could not sign student in", Toast.LENGTH_LONG);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setBackgroundColor(Color.TRANSPARENT);
            toast.show();
            return false;
        }else
        {
            // if success, tell the user
            Toast toast = Toast.makeText(getBaseContext(), "Student Successfully signed in", Toast.LENGTH_LONG);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setBackgroundColor(Color.TRANSPARENT);
            toast.show();

            // refresh the screen
            refresh(null);

            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_view, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_register_view, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
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
