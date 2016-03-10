package team2beat.com.qrcodes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ClassDetails extends AppCompatActivity {

    TextView lecture, title, room, location, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

        lecture = (TextView) findViewById(R.id.textLecture);
        title = (TextView) findViewById(R.id.textTitle);
        room = (TextView) findViewById(R.id.textRoom);
        location = (TextView) findViewById(R.id.textLocation);
        date = (TextView) findViewById(R.id.textDate);

        Intent _intent = getIntent();
        Bundle bundleModuleSelected = _intent.getExtras();
        String lectureSelected = bundleModuleSelected.getString("classSelected");
        lecture.setText(lectureSelected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class_details, menu);
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