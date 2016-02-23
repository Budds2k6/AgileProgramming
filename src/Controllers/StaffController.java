// import.DataModels_Stuff

public class StaffController
{	
	// Constructor
	public StaffController ()
	{
		
	}
	
	// Creates a new staff user via the model
	public createStaff ()
	{
		if (loginCheck())
		{
			// TODO: Unauthorised access message
			return;
		}
		
		// TODO: Gain staff information from application_form
		String staffID;
		String jobID;
		
		Staff newStaff = new Staff (staffID, jobID)
		
		// Process new staff to model
		StaffModel staffModel = new StaffModel();
		staffModel.CreateStaff(newStaff);
		
		// TODO: Return redirect
	}
	
	// Deletes the staff user via the model
	public void deleteStaff ()
	{
		if (loginCheck())
		{
			// TODO: Unautorised access message
			return;
		}
		
		StaffModel staffModel = new StaffModel();
		string staffFromView;
		
		staffModel.deleteStaff (staffFromView);
		
		// TODO: Return redirect
	}
	
	public void viewStaff ()
	{
		if (loginCheck())
		{
			// TODO: Unauthorised access message
			return;
		}
		
		StaffModel staffModel = new StaffModel();
		
		List<Staff> staffList = staffModel.getStaffList();
		
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
	public bool loginCheck ()
	{
		// Return true if incorrectly logged-in
		if (true)
		{ return true; }
		else
		{ return false; }
	}
}