// import.DataModels_Stuff

package team2beat.com.src.Controllers;
import java.util.List;

import team2beat.com.src.Models.*;

public class ModuleController
{	
	// Constructor
	public ModuleController ()
	{
		
	}
	
	public void CreateModule ()
	{
		// Ensure correctly logged-in
		if (loginCheck())
		{
			// TODO: Unauthorised access message
			return;
		}
		
		// TODO: Gain module information from application_form
		String moduleID = "";
		String name = "";
		String coordinator = "";

		ModuleModel newModule = new ModuleModel (moduleID, name, coordinator);

		// Process new module to model
		ModuleModel moduleModel = new ModuleModel();
		moduleModel.createModule(newModule);

		// TODO: Return redirect
	}

	// Signals to delete the specified module via the model
	public void deleteModule ()
	{
		// Ensure correctly logged-in
		if (loginCheck())
		{
			// TODO: Unauthorised access message
			return;
		}

		ModuleModel moduleModel = new ModuleModel();
		String moduleFromView = "";
		
		moduleModel.deleteModule(moduleFromView);
		
		// TODO: Return redirect
	}
	
	// Returns all of the modules
	public List<ModuleModel> viewModules ()
	{
		if (loginCheck())
		{ 
			// TODO: Unauthorised access message
			return null;
		}
		
		ModuleModel moduleModel = new ModuleModel();
		
		return moduleModel.getModuleList();
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
	private boolean loginCheck ()
	{
		// Return true if incorrectly logged-in
		if (true)
		{ return true; }
		else
		{ return false; }
	}
}