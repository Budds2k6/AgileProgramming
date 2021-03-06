package team2beat.com.src.AsyncClasses;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Matt on his laptop.
 */
public class EndClassAsync   extends ActionBarActivity {

    Connection _conn;
    String booking_id;
    String theFlag;


    // Constructor
    public EndClassAsync(String bid, String fl)
    {
        this.booking_id = bid;
        this.theFlag = fl;

        new PostClass().execute(bid,theFlag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PostClass().execute(booking_id,theFlag);
    }


    private class PostClass extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {

            // the url of the java servlet that does the operation
            String url = "http://silva.computing.dundee.ac.uk/2015-agileteam2/CreateBooking";

            // resource: http://hayageek.com/android-http-post-get/
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            // the parameters to send through
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("book",params[0]));       // booking id
            nameValuePairs.add(new BasicNameValuePair("flag", params[1]));      // Create or Update

            // attach the parameters to the request
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            } catch (Exception e) {
                e.printStackTrace();
            }

            // execute the request
            try {
                httpClient.execute(httpPost);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // end the function
            return null;
        }
    }
}
