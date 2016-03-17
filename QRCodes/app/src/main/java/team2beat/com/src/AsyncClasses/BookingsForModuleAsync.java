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
public class BookingsForModuleAsync extends ActionBarActivity {

    int moduleID;
    public ArrayList<String[]> toReturn;
    public boolean complete = false;

    // Constructor
    public BookingsForModuleAsync(int modID)
    {
        this.moduleID = modID;
        new PostClass().execute(String.valueOf(moduleID));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        new PostClass().execute(String.valueOf(moduleID));
    }


    private class PostClass extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {

            // the url of the java servlet that carries out the operations
            String url = "http://silva.computing.dundee.ac.uk/2015-agileteam2/BookingsForModule";   // TODO: Change this

            // resource: http://hayageek.com/android-http-post-get/
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            // create the parameters - requires a string for the name of the parameter and the actual value
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("module_id", params[0]));

            // attach the parameters to the request
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            } catch (Exception e) {
                e.printStackTrace();
            }

            // get the response after executing the request
            try {
                HttpResponse response = httpClient.execute(httpPost);

                String responseStr = EntityUtils.toString(response.getEntity());

                // this small piece of code was taken from:	----------------------------------------
                //http://www.tutorialspoint.com/java_xml/java_dom_create_document.htm
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(responseStr)));
                Element rootElement = document.getDocumentElement();
                // up to here ----------------------------------------------------------------------

                NodeList listChildren = rootElement.getChildNodes();

                toReturn = new ArrayList<String[]>();

                for(int i = 0; i < listChildren.getLength();i++) {
                    String[] temp = new String[13];

                    Element curr = (Element) listChildren.item(i);


                    // get the other details
                    temp[0] = getElementFromTag("booking_id", curr);
                    temp[1] = getElementFromTag("class_id", curr);
                    temp[2] = getElementFromTag("start_time", curr);
                    temp[3] = getElementFromTag("end_time", curr);
                    temp[4] = getElementFromTag("the_date", curr);
                    temp[5] = getElementFromTag("location_id", curr);
                    temp[6] = getElementFromTag("staff_id", curr);
                    temp[7] = getElementFromTag("attendance_list_id", curr);
                    temp[8] = String.valueOf(moduleID);
                    temp[9] = getElementFromTag("class_name", curr);
                    temp[10] = getElementFromTag("class_type", curr);
                    temp[11] = getElementFromTag("room_number", curr);
                    temp[12] = getElementFromTag("building", curr);

                    toReturn.add(temp);
                }

                complete = true;
                System.out.println("");

            } catch (Exception e) {
                e.printStackTrace();
            }

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


