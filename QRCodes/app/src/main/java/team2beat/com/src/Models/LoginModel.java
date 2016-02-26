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





import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


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
	private GoogleApiClient client;
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

/*
	public boolean theThings(String username, String password)
	{
		try {
			String url = "http://silva.computing.dundee.ac.uk/Agile/Login";
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection)obj.openConnection();

			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent","Mozilla/5.0");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

			//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
			String urlParameters = ("username="+username+"&password="+password);



			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

			return true;


		}catch(Exception e){

			e.printStackTrace();

		}

		return false;
	}

*/
	/*
	public boolean doLoginNew(String username, String password)
	{
		String url = "http://silva.computing.dundee.ac.uk/Agile/Login";

		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("username", username));
		nameValuePairs.add(new BasicNameValuePair("password", password));

		try
		{

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		}catch(Exception e){
			e.printStackTrace();
		}

		try
		{

			HttpResponse response = httpClient.execute(httpPost);

			Log.d("RESPONSE: ",  response.toString());

		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("PINEAPPLE");
		}

		return false;

	}
*/

	private class PostClass extends AsyncTask<String, Void, Void> {

		protected Void doInBackground(String... params) {

			String url = "http://silva.computing.dundee.ac.uk/Agile/Login";

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

				Log.d("RESPONSE: ", response.toString());
				System.out.println("RESPONSE: " + response.toString());
				String allofthethings = response.toString();
				System.out.println("RESPONSE: " + response.toString());

				String responseStr = EntityUtils.toString(response.getEntity());
				System.out.println("RESPONSE: " + response.toString());

				//theList = (String[]) request.getAttribute("list");

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("PINEAPPLE");
			}

			//return false;


			return null;


		}

/*
		protected void onPostExecute(String... params) {

			String url = "http://silva.computing.dundee.ac.uk/Agile/Login";

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

				Log.d("RESPONSE: ", response.toString());
				System.out.println("RESPONSE: " + response.toString());
				String allofthethings = response.toString();
				System.out.println("RESPONSE: " + response.toString());

				String responseStr = EntityUtils.toString(response.getEntity());
				System.out.println("RESPONSE: " + response.toString());


			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("PINEAPPLE");
			}

			//return false;


			//return null;


		}
*/

	}


}
