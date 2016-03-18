package team2beat.com.qrcodes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import team2beat.com.src.Controllers.AttendeeListController;
import team2beat.com.src.Controllers.ModuleController;
import team2beat.com.src.DataObjects.Attendee;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Module;
import team2beat.com.src.DataObjects.ShouldAttend;
import team2beat.com.src.DataObjects.Student;
import team2beat.com.src.Models.CustomComparatorDate;

public class module_statistics extends AppCompatActivity {

    ListView classList;
    ArrayList<String> list;
    ListAdapter adapter;
    //String[] foldString;
    ArrayList<ShouldAttend> studentList;
    ArrayList<Booking> bookingList;
    public static Module moduleSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_statistics);

        // set the text view value
        TextView moduleText = (TextView) findViewById(R.id.textView3);
        moduleText.setText(moduleSelected.getModuleName());

        classList = (ListView) findViewById(R.id.foldList);

        // read students
        ModuleController mc = new ModuleController();
        studentList = mc.getStudentListForModule(moduleSelected.getModuleCode());

        // read bookings
        mc = new ModuleController();
        bookingList = mc.getBookingListForModule(moduleSelected.getModuleCode());

        // sort the booking list by date
        Collections.sort(bookingList, new CustomComparatorDate());

        AttendeeListController alc = new AttendeeListController();

        int totalAttended = 0;
        int totalExpected = 0;

        // calculate the total attendance for the module - all ENDED classes
        for(int i = 0; i < bookingList.size(); i++)
        {
            // if the current class has ended
            if(bookingList.get(i).getEndTime() != null)
            {
                // add the values (who has attended and who should have attended) to the relevant fields
                ArrayList<Attendee> list = alc.getAttendanceListByID(Integer.valueOf(bookingList.get(i).getAttListID()));
                ArrayList<ShouldAttend> list2 = alc.getShouldAttend(Integer.valueOf(bookingList.get(i).getBookingID()));

                totalAttended += list.size();
                totalExpected += list2.size();
            }
        }

        // output the percent
        TextView txtPercentage = (TextView)findViewById(R.id.txtPercent);
        String percentString = calculateAttendancePercentage(totalAttended, totalExpected);

        // check for errors
        if(percentString.contains("NaN"))
        {
            percentString = "0%";
        }
        txtPercentage.setText(percentString);

        // display the list of classes
        displayClassesFold(null);

    }


    String calculateAttendancePercentage(int attended, int missing)
    {
        // calculate the percentage of the attendance
        int total = attended + missing;

        float percent = ((float)attended / (float)total) * 100;

        DecimalFormat df = new DecimalFormat("#.#");

        // return as a string
        String returnVal = "Total Attendance: \n" + df.format(percent) + "%";

        return returnVal;
    }

    @Override
    public void onBackPressed() {
    }

    public void displayClassesFold(View view){

        // the list to display the items
        classList = (ListView) findViewById(R.id.foldList);


        // add all the items
        list = new ArrayList<>();
        for (int i = 0; i < bookingList.size(); i++) {
            // date time place type
            String toAdd = "";
            String start = String.valueOf(bookingList.get(i).getStartTime());
            String room = String.valueOf(bookingList.get(i).getBuilding()) + " " + String.valueOf(bookingList.get(i).getRoomNumber());
            String date = String.valueOf(bookingList.get(i).getDate());
            String type = String.valueOf(bookingList.get(i).getClassType());

            if(bookingList.get(i).getEndTime() != null)
            {
                toAdd += "*";
            }

            toAdd += date + " | " + start + " | " + type + " | " + room;

            list.add(toAdd);
        }

        if(list.size() > 0)
        {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

            classList.setAdapter(adapter);

            classList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ClassDetails.theBooking = bookingList.get(position);
                    Intent i = new Intent(getBaseContext(), ClassDetails.class);
                    //i.putExtra("classSelected", ((TextView) view).getText());
                    module_statistics.this.startActivity(i);
                }
            });


            // if there are no classes for that module, output a message
        }
        else
        {
            list.add("NO CLASSES FOR THIS MODULE");

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

            classList.setAdapter(adapter);

            classList.setOnItemClickListener(null);     // shouldn't do anything when clicked
        }
    }

    // display the student list
    public void displayStudentFold(View view){

        classList = (ListView) findViewById(R.id.foldList);

        // get the student details
        list = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            list.add(studentList.get(i).getStudentName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        classList.setOnItemClickListener(null);     // does not go anywhere when clicked
        // TODO: onClick will take the user to the information about the student's attendance

        classList.setAdapter(adapter);

    }

    // go bacl to the staff modules page
    public void goBack(View v)
    {
        Intent i = new Intent (getBaseContext(), staff_modules.class);
        module_statistics.this.startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_module_statistics, menu);
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
}
