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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Matt on .
 */
public class BookingAsync  extends ActionBarActivity {


    String lec_id;
    String loc_id;
    String staff_id;
    String theFlag;
    public String [] returnedId;

    public BookingAsync (String le,String lo, String sid, String fl){

        this.lec_id = String.valueOf(le);
        this.loc_id = String.valueOf(lo);
        this.staff_id = sid;
        this.theFlag = fl;

        new PostClass().execute(lec_id,loc_id,staff_id,theFlag);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        new PostClass().execute(lec_id, loc_id, staff_id, theFlag);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private class PostClass extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {

            String url = "http://silva.computing.dundee.ac.uk/2015-agileteam2/CreateBooking";

            // resource: http://hayageek.com/android-http-post-get/
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("lec_id", params[0]));
            nameValuePairs.add(new BasicNameValuePair("loc_id", params[1]));
            nameValuePairs.add(new BasicNameValuePair("staff_id", params[2]));
            nameValuePairs.add(new BasicNameValuePair("flag", params[3]));

            try {

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

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

                // return the attendanceList id and the booking id
                String[] returned = new String[2];

                returned[0] = getElementFromTag("AttListID", rootElement);
                returned[1] = getElementFromTag("booking", rootElement);

                returnedId = new String[2];
                returnedId = returned;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("PINEAPPLE");
            }

            //return false;

            return null;

        }


    }

    protected String getElementFromTag(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }

        return null;
    }

}


