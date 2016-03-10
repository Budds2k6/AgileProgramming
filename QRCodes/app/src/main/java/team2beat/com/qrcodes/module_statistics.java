package team2beat.com.qrcodes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class module_statistics extends AppCompatActivity {
    ListView classList;
    ArrayList<String> list;
    ListAdapter adapter;
    String[] foldString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_statistics);

        Intent _intent = getIntent();
        Bundle bundleModuleSelected = _intent.getBundleExtra("moduleSelected");
        String moduleSelected = bundleModuleSelected.getString("moduleSelected");
        TextView moduleText = (TextView) findViewById(R.id.textView3);
        moduleText.setText(moduleSelected);

        classList = (ListView) findViewById(R.id.foldList);
        foldString = new String[]{"Wolfson 0900", "Dalhousie 1S05F12b 1400"};


        list = new ArrayList<String>();
        for (int i = 0; i < foldString.length; i++) {
            list.add(foldString[i]);

        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        classList.setAdapter(adapter);

    }


    public void displayClassesFold(View view){
        classList = (ListView) findViewById(R.id.foldList);
        foldString = new String[]{"Wolfson 0900", "Dalhousie 1S05F12b 1400"};


        list = new ArrayList<>();
        for (int i = 0; i < foldString.length; i++) {
            list.add(foldString[i]);

        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        classList.setAdapter(adapter);

    }
    public void displayStudentFold(View view){
        classList = (ListView) findViewById(R.id.foldList);
        foldString = new String[]{"Ryan Robinson", "Lew Bobobo Bo-bo Bobo Bo", "Andy Cobley"};


        list = new ArrayList<>();
        for (int i = 0; i < foldString.length; i++) {
            list.add(foldString[i]);

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
