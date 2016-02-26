package team2beat.com.src.Models;

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

public class LoginModel extends ActionBarActivity {
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	//private GoogleApiClient client;
	private String username;
	private String password;
	public String [] theReturns;

	// Class Constructor
	public LoginModel(String u, String p)
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

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		//client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}


	private class PostClass extends AsyncTask<String, Void, Void> {

		protected Void doInBackground(String... params) {

			String url = "http://silva.computing.dundee.ac.uk/2015-agileteam2/Login";

			//http://silva.computing.dundee.ac.uk/2015-agileteam2/


			HttpClient httpClient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

			nameValuePairs.add(new BasicNameValuePair("username", params[0]));
			nameValuePairs.add(new BasicNameValuePair("password", params[1]));

			try {

				httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				HttpResponse response = httpClient.execute(httpPost);


				String responseStr = EntityUtils.toString(response.getEntity());


				System.out.println("RESPONSE: " + response.toString());


				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(responseStr)));
				Element rootElement = document.getDocumentElement();

				theReturns = new String [4];
				theReturns[0] = getElementFromTag("username", rootElement);




				if (!theReturns[0].equals("Login Failed"))
				{theReturns[1] = getElementFromTag("password", rootElement);
				theReturns[2] = getElementFromTag("firstname", rootElement);
				theReturns[3] = getElementFromTag("surname", rootElement);
				}
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
