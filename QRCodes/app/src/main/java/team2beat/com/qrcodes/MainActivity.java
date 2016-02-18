package team2beat.com.qrcodes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String SCAN = "com.google.zxing.client.android.SCAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void ScanQR(View v)
    {
        try
        {
            // Create a new 'Intent to scan'
            Intent intent = new Intent(SCAN);
            // Set up a variable called 'Scan Mode' which tells us which type of scan it will be doing (QR)
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");

            // Start the activity - i.e. look for a QR Code
            startActivityForResult(intent, 0);

            // If the activity does not exist - i.e. no scanner found
        }catch (ActivityNotFoundException e)
        {

            showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No");

        }
    }

    private Dialog showDialog(final Activity activity, CharSequence title, CharSequence message,CharSequence Yes, CharSequence No)
    {

        AlertDialog.Builder download = new AlertDialog.Builder(activity);

        download.setTitle(title);
        download.setMessage(message);

        download.setPositiveButton(Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Go to a Scanner on the market
                Uri uri = Uri.parse("market://search?q-pname:" + "com.google.zxing.client.android");

                try {

                    activity.startActivity(activity.getIntent());

                } catch (ActivityNotFoundException e) {
                }

            }
        });

        download.setNegativeButton(No, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int intf)
           {

           }

        });


        return download.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == 0) {
            if(resultCode == RESULT_OK)
            {
                // Is called when activity exists. Gives the result it found - with any additional data
                String contents = intent.getStringExtra("SCAN_RESULT");

                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Toast toast = Toast.makeText(this, "Content " + contents + "Format: " + format, Toast.LENGTH_LONG);

                toast.show();

            }
        }
    }

}