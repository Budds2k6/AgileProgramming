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

import java.util.ArrayList;

import team2beat.com.src.Controllers.ModuleController;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Module;
import team2beat.com.src.DataObjects.ShouldAttend;
import team2beat.com.src.DataObjects.Student;

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

        //Intent _intent = getIntent();
        //Bundle bundleModuleSelected = _intent.getExtras();


        //String moduleSelected = bundleModuleSelected.getString("moduleSelected");
        //moduleSelected = (Module) bundleModuleSelected.getParcelable("moduleSelected");

        TextView moduleText = (TextView) findViewById(R.id.textView3);
        moduleText.setText(moduleSelected.getModuleName());

        classList = (ListView) findViewById(R.id.foldList);





        // read students
        ModuleController mc = new ModuleController();
        studentList = mc.getStudentListForModule(moduleSelected.getModuleCode());
        //studentList = new ArrayList<>();

        // read bookings
        mc = new ModuleController();
        bookingList = mc.getBookingListForModule(moduleSelected.getModuleCode());
        //bookingList = new ArrayList<>();



        //foldString = new ArrayList<String>();


        //list = new ArrayList<String>();
        //for (int i = 0; i < bookingList.size(); i++) {
        //    list.add(String.valueOf(bookingList.get(i).getStartTime()));
        //}

        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        //classList.setAdapter(adapter);

        displayClassesFold(null);

    }


    public void displayClassesFold(View view){
        classList = (ListView) findViewById(R.id.foldList);



        list = new ArrayList<>();
        for (int i = 0; i < bookingList.size(); i++) {
            // date time place type
            String toAdd;
            String start = String.valueOf(bookingList.get(i).getStartTime());
            String room = String.valueOf(bookingList.get(i).getBuilding()) + " " + String.valueOf(bookingList.get(i).getRoomNumber());
            String date = String.valueOf(bookingList.get(i).getDate());
            String type = String.valueOf(bookingList.get(i).getClassType());

            toAdd = date + " | " + start + " | " + type + " | " + room;

            list.add(toAdd);

        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        classList.setAdapter(adapter);

        classList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassDetails.theBooking = bookingList.get(position);
                Intent i = new Intent(getBaseContext(), ClassDetails.class);
                i.putExtra("classSelected", ((TextView) view).getText());
                module_statistics.this.startActivity(i);
            }
        });

    }
    public void displayStudentFold(View view){
        classList = (ListView) findViewById(R.id.foldList);


        list = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            list.add(studentList.get(i).getStudentName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        classList.setAdapter(adapter);

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
