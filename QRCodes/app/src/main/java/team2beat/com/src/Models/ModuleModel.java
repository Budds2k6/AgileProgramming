package team2beat.com.src.Models;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import team2beat.com.src.AsyncClasses.BookingsForModuleAsync;
import team2beat.com.src.AsyncClasses.StaffAsync;
import team2beat.com.src.AsyncClasses.StudentsForModuleAsync;
import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.Module;
import team2beat.com.src.DataObjects.ShouldAttend;

//Refactoring - ModuleModel's details were formerly in BookingModel - it now has its own class
public class ModuleModel
{
	private String moduleID;
	private String name;
	private String coordinator;
	
	// Class Constructor
	public ModuleModel()
	{}

	public ModuleModel(String mID, String n, String c)
	{
		this.moduleID = mID;
		this.name = n;
		this.coordinator = c;
	}
	
	// Creates a new module, and returns the ID
	public String createModule (ModuleModel m)
	{
		try
		{
			
		}
		catch (Exception e)
		{
			
		}
		return "1";

	}
	
	public void editModule (String moduleID)
	{
		
	}

	public void deleteModule (String moduleID)
	{

	}

	public List<ModuleModel> getModuleList()
	{
		return null;
	}

	public ArrayList<ShouldAttend> getStudentsForModule(String moduleID)
	{

		ArrayList<String[]> returnData = new ArrayList<String[]>();

		StudentsForModuleAsync sfma = new StudentsForModuleAsync(moduleID);


		while (!sfma.complete) {
			returnData = sfma.toReturn;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//create modules out of the data
		ArrayList<ShouldAttend> studentList = new ArrayList<ShouldAttend>();

		for(int i = 0; i < returnData.size(); i++)
		{
			ShouldAttend m = new ShouldAttend(returnData.get(i)[0] + " " + returnData.get(i)[1], returnData.get(i)[2]);
			studentList.add(m);
		}

		return studentList;
	}

	public ArrayList<Booking> getBookingsForModule(String moduleID)
	{
		ArrayList<String[]> returnData = new ArrayList<String[]>();

		BookingsForModuleAsync bfma = new BookingsForModuleAsync(moduleID);


		while (!bfma.complete) {
			returnData = bfma.toReturn;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//create modules out of the data
		ArrayList<Booking> studentList = new ArrayList<Booking>();

		for(int i = 0; i < returnData.size(); i++)
		{
			String[] currBooking = returnData.get(i);
			//Booking b = new Booking();

			// convert 2 and 3 to Times, 4 to dates.
			Time timeS = null;
			Time timeE = null;
			Date dateS = null;

			try {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				timeS = new Time(formatter.parse(currBooking[2]).getTime());
				timeE = new Time(formatter.parse(currBooking[3]).getTime());
				dateS = new Date(formatter.parse(currBooking[2]).getDate());
			}catch(Exception e){}


			Booking b = new Booking (currBooking[0], currBooking[1], timeS, timeE, dateS, currBooking[4], currBooking[5], currBooking[6], currBooking[7], currBooking[8], currBooking[9], currBooking[10], currBooking[11]);
			studentList.add(b);
		}

		return studentList;
	}

}
