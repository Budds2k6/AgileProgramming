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
 * Created by Matt on his laptop.
 */
public class RegisterAsync extends ActionBarActivity
{
    private String username;
    private String booking_id;
    public String [] toReturn;

    public RegisterAsync (String bid, String un){

        this.booking_id = bid;
        this.username = un;

        new PostClass().execute(booking_id,username);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new PostClass().execute(booking_id,username);
    }


    private class PostClass extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {

            // url of the Java servlet which does the actual operations
            String url = "http://silva.computing.dundee.ac.uk/2015-agileteam2/Register";

            // resource: http://hayageek.com/android-http-post-get/
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            // set the parameters to pass
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("booking_id", params[0]));
            nameValuePairs.add(new BasicNameValuePair("username", params[1]));

            // attach the parameters to the request
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            } catch (Exception e) {
                e.printStackTrace();
            }

            // get the response from the request
            try {

                HttpResponse response = httpClient.execute(httpPost);

                String responseStr = EntityUtils.toString(response.getEntity());


                // this small chunk was taken from:	------------------------------------------------
                //http://www.tutorialspoint.com/java_xml/java_dom_create_document.htm

                // build an XML file from the returned code
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(responseStr)));
                Element rootElement = document.getDocumentElement();
                // up to here ----------------------------------------------------------------------

                // extract the attendanceList id and the booking id from thr XML file
                String[] returned = new String[2];
                returned[0] = getElementFromTag("username", rootElement);
                returned[1] = getElementFromTag("success", rootElement);

                toReturn = returned;

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("PINEAPPLE broken");
            }

            // end the function
            return null;
        }
    }


    // function adapted from: http://stackoverflow.com/questions/4076910/how-to-retrieve-element-value-of-xml-using-java
    protected String getElementFromTag(String tagName, Element element) {

        // get all of the elements in the XML that match the tag
        NodeList list = element.getElementsByTagName(tagName);

        // if the list contains at least 1 element...
        if (list != null && list.getLength() > 0) {

            // get the first one and return it
            NodeList subList = list.item(0).getChildNodes();

            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }

        // return null if something goes wrong
        return null;
    }
}
