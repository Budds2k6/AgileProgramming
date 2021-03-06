package team2beat.com.qrcodes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.CountDownTimer;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import android.graphics.Bitmap;
import android.graphics.Color;

import team2beat.com.src.Controllers.BookingController;
import team2beat.com.src.DataObjects.Staff;
import team2beat.com.src.DataObjects.Student;




public class MainActivity extends AppCompatActivity {

    static final String SCANNER_DOWNLOAD_LOCATION = "com.google.zxing.client.android.SCAN";
    public static Student studentDetails;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_student_main);

            //Bundle detailsBundle = getIntent().getExtras();

            // extract the details from the Bundle which was passed
            //studentDetails = (Student) detailsBundle.getSerializable("details");

            // display the user's name on the screen
            TextView txtName = (TextView) findViewById(R.id.lblLoggedInAs);
            txtName.setText("Logged In As: " + studentDetails.getFirstName() + " " + studentDetails.getSurname());

        }catch(Exception e){

            e.printStackTrace();
        }

    }


    // code adapted from:
    // http://examples.javacodegeeks.com/android/android-barcode-and-qr-scanner-example/
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
            // if the ZXing scanner is not found, ask the user if they wish to download one
            showDialog(MainActivity.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    public void displayToast()
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.toastLayout));
        Toast toast = new Toast(this);
        toast.setView(view);
        toast.show();
    }
    // code adapted from:
    // http://examples.javacodegeeks.com/android/android-barcode-and-qr-scanner-example/
    private Dialog showDialog(final Activity activity, CharSequence title, CharSequence message, CharSequence Yes, CharSequence No) {

        // create a dialog message
        AlertDialog.Builder downloadMessage = new AlertDialog.Builder(activity);
        downloadMessage.setTitle(title);
        downloadMessage.setMessage(message);

        // set up the confirm / yes button
        downloadMessage.setPositiveButton(Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int intf) {

                // Go to a Scanner on the market
                Uri downloadLocation = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");

                Intent intent = new Intent(Intent.ACTION_VIEW, downloadLocation);

                try {
                    activity.startActivity(intent);

                } catch (ActivityNotFoundException e) {
                }

            }
        });

        // the Cancel / No button will just close the dialog message
        downloadMessage.setNegativeButton(No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int intf) {

            }

        });

        // show the dialog box
        return downloadMessage.show();
    }


    // code adapted from:
    // http://examples.javacodegeeks.com/android/android-barcode-and-qr-scanner-example/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        // if the scan was successful - i.e. it found a QR Code
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                Vibrator vibrate;
                long[] once = { 0, 100 };
                vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);

                if (vibrate != null) {
                    vibrate.vibrate(once, -1);
                }



                // called when activity exists. Gives the result it found - with any additional data
                String contents = intent.getStringExtra("SCAN_RESULT");

                // try to parse the recognised string into an int,
                try {
                    //Cast string content to integer (bookingID needs to be an int for sending)
                    int bookingContent = Integer.parseInt(contents);

                    // TODO get actual data to send
                    // LEGIT LEWIS YOU NEED TO DO THIS NEXT
                    // IT'S IMPORTANT

                    PresentRecord pr = new PresentRecord(bookingContent, studentDetails.getStudentID());

                    sendAttendanceToDatabase(pr);

                    // Display it on the form

                    TextView theText = (TextView) findViewById(R.id.textView5);
                    theText.setText(contents);

                    // tell the user that they scanned in
                    //Toast toast = Toast.makeText(this, "SUCCESSFULLY SCANNED INTO CLASS" + contents, Toast.LENGTH_LONG);
                    //displayToast();
                    //toast.show();


                    // if the string does not convert, catch the exception and inform the user
                }catch (Exception e)
                {
                    Toast toast = Toast.makeText(this, "QR Code not recognised", Toast.LENGTH_LONG);

                    ErrorFeedback();

                    TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastMessage.setBackgroundColor(Color.TRANSPARENT);

                    toast.show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void ConfirmFeedback()
    {
        CountDownTimer ConfirmDisplayTimer;
        long duration = 4000;
        long timeInterval = 1000;

        new CountDownTimer(duration,timeInterval) {

            @Override
            public void onTick(long millisUntilFinished) {

                // set the image and text to the relevant items
                TextView Confirmlbl = (TextView) findViewById(R.id.textView5);
                Confirmlbl.setText("You have been signed in!");

                ImageView Confirmed = (ImageView) findViewById(R.id.imageView2);
                Confirmed.setImageResource(R.drawable.checkmark);

                Confirmed.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                DefaultScreen();
            }

        }.start();

    }

    // timer to show an error image if the scan was unsuccessful
    public void ErrorFeedback()
    {
        CountDownTimer ErrDisplayTimer;
        long duration = 4000;
        long timeInterval = 1000;

        new CountDownTimer(duration,timeInterval) {

            @Override
            public void onTick(long millisUntilFinished) {

                // set the image and text to the relevant items
                TextView QRErrorlbl = (TextView) findViewById(R.id.textView5);
                QRErrorlbl.setText("Please Scan again");

                ImageView QRError = (ImageView) findViewById(R.id.imageView2);
                QRError.setImageResource(R.drawable.crossmark);

                QRError.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFinish() {
                    DefaultScreen();
            }

        }.start();
    }

    public void DefaultScreen()
    {
        // set up the default values
        TextView defTxt = (TextView) findViewById(R.id.textView5);
        defTxt.setText("Please Scan a QR code");

        ImageView defImg = (ImageView) findViewById(R.id.imageView2);
        defImg.setVisibility(View.INVISIBLE);
    }

    /*
    *
    * THE FUNCTION TO SIGN A STUDENT IN TO A CLASS
    *
    * */
    public void sendAttendanceToDatabase(PresentRecord pr)
    {

        BookingController bc = new BookingController();

        // sign the student in to the class - return a boolean to indicate success (or lack of!)
        boolean studentReturned = bc.setAttendance(pr);

        if(studentReturned)
        {
            ConfirmFeedback();
        }else
        {
            ErrorFeedback();
        }
    }

}