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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Lecture;
import team2beat.com.src.DataObjects.Location;
import team2beat.com.src.DataObjects.Module;

//Refactored - code for BookingModel moved to servlet due to the necessity of them being on the server

public class BookingModel  extends ActionBarActivity
{
	Connection _conn;
	String lec_id;
	String loc_id;
	String staff_id;
	String theFlag;
	public String [] returnedId;


	// Constructor
	public BookingModel(int le,int lo, String sid, String fl)
	{
		this.lec_id = String.valueOf(le);
		this.loc_id = String.valueOf(lo);
		this.staff_id = sid;
		this.theFlag = fl;

		new PostClass().execute(lec_id,loc_id,staff_id,theFlag);
	}

	private void fudgeMethod (Connection conn)
	{
		// FIXME: Replace method with actual connection string
		this._conn = conn;
	}

	// Creates a new booking
	public int createBooking (Booking thisBooking)
	{
		// TODO: Access the database, and return the ID
		return 0;
	}

	// Sets the student present
	public void setStudentPresent (String studentID, int bookingID)
	{
		// TODO: Access database, and set attendance
		CallableStatement callState = null;

		try
		{
			// FIXME: Replace with correct Stored Procedure call
			String query = "{call TEMP_PROCEDURE (?, ?)}";
			callState = _conn.prepareCall(query);

			callState.close();
		}
		catch (Exception e)
		{

		}
	}

	// Set the reason for absence
	public void updateAttendanceReason (String studentID, int bookingID, String moduleID, String reason)
	{
		// TODO: Access the database, and set reason
	}

	// Obtains a list of bookings
	public List<Booking> getBookingList (String staffID)
	{
		// TODO: Un-fudge
		Statement temp = null;
		String query = "SELECT * FROM Booking";

		List<Booking> bookingList = new ArrayList<Booking>();

		try
		{
			temp = _conn.createStatement();

			ResultSet resultSet = temp.executeQuery(query);

			//Refactored
			while (resultSet.next())
			{
				String bookingID = resultSet.getString(1);
				String lectureID = resultSet.getString(2);
				Time start = resultSet.getTime(3);
				Date theDate = resultSet.getDate(3);
				Time end = resultSet.getTime(4);
				String locationID = resultSet.getString(5);
				String staffName = resultSet.getString(6);
				String attListID = resultSet.getString(7);
				String moduleID = resultSet.getString(8);
				String moduleName = resultSet.getString(9);
				String lectType = resultSet.getString(10);
				String roomNo = resultSet.getString(11);
				String building = resultSet.getString(12);


				//Refactoring - initially details within BookingModel, location, module and lecture
				// now have their own data structures to store info
				Location thisLocation = new Location (locationID, roomNo, building);
				Module thisModule = new Module (moduleID, moduleName);
				Lecture thisLecture = new Lecture (lectureID, moduleID, Lecture.LectType.valueOf(lectType));

				Booking thisBooking = new Booking (start, end, theDate, attListID, thisLocation, thisModule, thisLecture);

				bookingList.add(thisBooking);
			}

			temp.close();
		}
		catch (Exception e)
		{
			// TODO: No results exception
		}

		return bookingList;
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView();
		new PostClass().execute(lec_id,loc_id,staff_id,theFlag);

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		//client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
	}


	private class PostClass extends AsyncTask<String, Void, Void> {

		protected Void doInBackground(String... params) {

			String url = "http://silva.computing.dundee.ac.uk/2015-agileteam2/CreateBooking";

			//http://silva.computing.dundee.ac.uk/2015-agileteam2/


			HttpClient httpClient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(url);

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();



			nameValuePairs.add(new BasicNameValuePair("lec_id",params[0]));
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


				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new InputSource(new StringReader(responseStr)));
				Element rootElement = document.getDocumentElement();

				//theReturns = new String [4];
				///theReturns[0] = getElementFromTag("username", rootElement);

				String [] returned = new String [2];


				returned[0] = getElementFromTag("AttListID",rootElement);
				returned[1] = getElementFromTag("booking",rootElement);


				returnedId = new String [2];
				returnedId = returned;

				//if (!theReturns[0].equals("Login Failed"))
				//{theReturns[1] = getElementFromTag("password", rootElement);
				//	theReturns[2] = getElementFromTag("firstname", rootElement);
				//	theReturns[3] = getElementFromTag("surname", rootElement);
				//}
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