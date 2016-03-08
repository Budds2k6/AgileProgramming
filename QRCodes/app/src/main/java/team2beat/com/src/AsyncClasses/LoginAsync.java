package team2beat.com.src.AsyncClasses;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;


import android.os.Bundle;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Matt on his laptop.
 */
public class LoginAsync  extends ActionBarActivity{

    private String username;
    private String password;
    public String [] toReturn;

    // Class Constructor
    public LoginAsync(String u, String p)
    {
        this.username = u;
        this.password = p;

        new PostClass().execute(username, password);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        new PostClass().execute(username, password);
    }


    private class PostClass extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {

            // the url of the java servlet that carries out the operations
            String url = "http://silva.computing.dundee.ac.uk/2015-agileteam2/Login";

            // resource: http://hayageek.com/android-http-post-get/
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            // create the parameters - requires a string for the name of the parameter and the actual value
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("username", params[0]));
            nameValuePairs.add(new BasicNameValuePair("password", params[1]));

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

                // get the first type of user from the XML - either Staff or Student
                String userType = rootElement.getTagName();

                // try to get the username
                toReturn = new String [5];
                toReturn[0] = getElementFromTag("username", rootElement);

                // if the username is not "Login Failed" - i.e. the Login succeeded...
                if (!toReturn[0].equals("Login Failed"))
                {

                    // get the other details
                    toReturn[1] = getElementFromTag("firstname", rootElement);
                    toReturn[2] = getElementFromTag("surname", rootElement);

                    // depending on whether the user is Staff or Student, get the ID number
                    if(userType.equals("staff"))
                    {
                        toReturn[3] = getElementFromTag("staff_id", rootElement);
                    }
                    else
                    {
                        toReturn[3] = getElementFromTag("student_id", rootElement);
                    }

                    // keep getting details
                    toReturn[4] = userType;
                }
            } catch (Exception e) {
                e.printStackTrace();
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
