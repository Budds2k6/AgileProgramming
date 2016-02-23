package team2beat.com.qrcodes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.graphics.Bitmap;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    static final String SCANNER_DOWNLOAD_LOCATION = "com.google.zxing.client.android.SCAN";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void ScanQR(View v) {
        try {
            // Create a new 'Intent to scan'
            Intent intent = new Intent(SCANNER_DOWNLOAD_LOCATION);
            // Set up a variable called 'Scan Mode' which tells us which type of scan it will be doing (QR)
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");

            // Start the activity - i.e. look for a QR Code
            startActivityForResult(intent, 0);

            // If the activity does not exist - i.e. no scanner found
        } catch (ActivityNotFoundException e) {
            showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    private Dialog showDialog(final Activity activity, CharSequence title, CharSequence message, CharSequence Yes, CharSequence No) {

        AlertDialog.Builder download = new AlertDialog.Builder(activity);

        download.setTitle(title);
        download.setMessage(message);

        download.setPositiveButton(Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Go to a Scanner on the market
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                try {

                    activity.startActivity(intent);

                } catch (ActivityNotFoundException e) {
                }

            }
        });

        download.setNegativeButton(No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int intf) {

            }

        });

        return download.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                // Is called when activity exists. Gives the result it found - with any additional data
                String contents = intent.getStringExtra("SCAN_RESULT");

                try {
                    //Cast string content to integer (bookingID needs to be an int for sending)
                    int bookingContent = Integer.parseInt(contents);

                    // TODO get actual data to send
                    sendAttendanceToDatabase(bookingContent, "12345678");

                    // Display it on the form
                    TextView theText = (TextView) findViewById(R.id.qrOutput);
                    theText.setText(contents);

                    Toast toast = Toast.makeText(this, "SUCCESSFULLY SCANNED INTO CLASSNAME", Toast.LENGTH_LONG);

                    toast.show();
                    
                }catch (Exception e)
                {
                    Toast toast = Toast.makeText(this, "QR Code not recognised", Toast.LENGTH_LONG);

                    toast.show();
                }
            }
        }
    }


    public void sendAttendanceToDatabase(int bookingID, String studentID)
    {
        PresentRecord record = new PresentRecord(bookingID, studentID);

        // TODO pass object to controller to write to database

        // TODO get class name from database

        // return "Class Name";
    }


    public void generateQRCode(View v) {
        String content = "101"; //Booking 1 on Monday morning (for example)

        // Create a QR Code Writer
        QRCodeWriter qrWriter = new QRCodeWriter();

        int width = 150;
        int height = 150;

        try
        {

            //BitMatrix bitMatrix = new BitMatrix(width, height);
            BitMatrix bitMatrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            for(int i = 0; i < width; i++)
            {
                for (int j = 0; j < height; j++)
                {
                    if (bitMatrix.get(i, j) == true)
                    {
                        bitmap.setPixel(i, j, Color.BLACK);
                    }else
                    {
                        bitmap.setPixel(i, j, Color.WHITE);
                    }
                }
            }


        }catch(Exception e){}

    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://team2beat.com.qrcodes/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://team2beat.com.qrcodes/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}