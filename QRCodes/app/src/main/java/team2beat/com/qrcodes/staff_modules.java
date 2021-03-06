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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import team2beat.com.src.DataObjects.Module;


public class staff_modules extends AppCompatActivity {

    public static ArrayList<Module> modules = new ArrayList<Module>();
    public static String loggedInStaff;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_modules);

        final ListView moduleList = (ListView) findViewById(R.id.moduleList);

        ArrayList<Module> sortedList = new ArrayList<>();

        // get the modules that are actually taught by the current user
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < modules.size(); i++) {
            if(modules.get(i).getCoordinator().equals(loggedInStaff)) {
                list.add(modules.get(i).getModuleName() + " (" + modules.get(i).getModuleCode() + ") - " + modules.get(i).getCoordinatorName());
                sortedList.add(modules.get(i));
            }
        }

        // get the other modules that they can access
        for (int i = 0; i < modules.size(); i++) {
            if(!modules.get(i).getCoordinator().equals(loggedInStaff)) {
                list.add(modules.get(i).getModuleName() + " (" + modules.get(i).getModuleCode() + ") - " + modules.get(i).getCoordinatorName());
                sortedList.add(modules.get(i));
            }
        }

        modules = null;
        modules = sortedList;

        // display the modules on the list
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        moduleList.setAdapter(adapter);

        moduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                module_statistics.moduleSelected = modules.get(position);
                Intent i = new Intent (getBaseContext(), module_statistics.class);
                staff_modules.this.startActivity(i);
            }
        });

    }

    public void goBack(View v)
    {
        Intent i = new Intent (getBaseContext(), StaffMainActivity.class);
        //i.putExtra("moduleSelected",  modules.get(position));
        staff_modules.this.startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_staff_modules, menu);
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
