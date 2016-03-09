// import.DataModels_Stuff

package team2beat.com.src.Controllers;
import java.util.List;

import team2beat.com.src.Models.*;

public class StaffController
{	
	// Constructor
	public StaffController ()
	{
		
	}
	
	// Creates a new staff user via the model
	public void createStaff ()
	{
		if (loginCheck())
		{
			// TODO: Unauthorized access message
			return;
		}
		
		// TODO: Gain staff information from application_form
		String staffID = "";
		String jobID = "";

		//StaffModel newStaff = new StaffModel (staffID, jobID);
		StaffModel newStaff = new StaffModel();

		// Process new staff to model
		StaffModel staffModel = new StaffModel();
		staffModel.createStaff(newStaff);
		
		// TODO: Return redirect
	}
	
	// Deletes the staff user via the model
	public void deleteStaff ()
	{
		if (loginCheck())
		{
			// TODO: Unauthorized access message
			return;
		}
		
		StaffModel staffModel = new StaffModel();
		String staffFromView = "";
		
		staffModel.deleteStaff(staffFromView);
		
		// TODO: Return redirect
	}
	
	public void viewStaff ()
	{
		if (loginCheck())
		{
			// TODO: Unauthorized access message
			return;
		}
		
		StaffModel staffModel = new StaffModel();
		
		List<StaffModel> staffList = staffModel.getStaffList();
		
		// TODO: Return list to view
	}
	
	// Performs logout method
	public void logout ()
	{
		/*
		* TODO: Logout method
		* Perform session logout
		* 	- Set session variables to null
		* 	- Redirect to login screen
		*/
	}
	
	// Ensures user is logged in
	public boolean loginCheck ()
	{
		// Return true if incorrectly logged-in
		if (true)
		{ return true; }
		else
		{ return false; }
	}
}