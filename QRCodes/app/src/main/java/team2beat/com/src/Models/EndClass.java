package team2beat.com.src.Models;

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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Lecture;
import team2beat.com.src.DataObjects.Location;
import team2beat.com.src.DataObjects.Module;

/**
 * Created by Matt on 26/02/2016.
 */




public class EndClass  extends ActionBarActivity
{
    Connection _conn;
    String booking_id;
    String theFlag;


    // Constructor
    public EndClass(String bid, String fl)
    {
        this.booking_id = bid;
        this.theFlag = fl;

        new PostClass().execute(bid,theFlag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        new PostClass().execute(booking_id,theFlag);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private class PostClass extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {

            String url = "http://silva.computing.ic_dundee1111.ac.uk/2015-agileteam2/CreateBooking";

            // resource: http://hayageek.com/android-http-post-get/
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            // the parameters to send through
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("book",params[0]));       // booking id
            nameValuePairs.add(new BasicNameValuePair("flag", params[1]));      // Create or Update

            try {

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

                // get the response from the command
                HttpResponse response = httpClient.execute(httpPost);

                String responseStr = EntityUtils.toString(response.getEntity());

                System.out.println("RESPONSE: " + response.toString());

                // this small chunk was taken from:	------------------------------------------------
                //http://www.tutorialspoint.com/java_xml/java_dom_create_document.htm

                // build an XML file from the returned code
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(responseStr)));
                Element rootElement = document.getDocumentElement();

                // up to here ----------------------------------------------------------------------
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            //return false;

            return null;

        }



    }}