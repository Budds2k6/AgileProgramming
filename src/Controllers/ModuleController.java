// import.DataModels_Stuff

public class StaffController
{	
	// Constructor
	public StaffController ()
	{
		
	}
	
	public void CreateModule ()
	{
		// Ensure correctly logged-in
		if (LoginCheck())
		{
			// TODO: Unauthorised access message
			return;
		}
		
		// TODO: Gain module information from application_form
		string moduleID;
		string name;
		string coordinator;
		
		Module newModule = new Module (moduleID, name, coordinator);
		
		// Process new module to model
		ModuleModel moduleModel = new ModuleModel();
		moduleModel.CreateModule(newModule);
		
		// TODO: Return redirect
	}
	
	// Signals to delete the specified module via the model
	public void deleteModule ()
	{
		// Ensure correctly logged-in
		if (LoginCheck())
		{
			// TODO: Unauthorised access message
			return;
		}
		
		ModuleModel moduleModel = new ModuleModel();
		string moduleFromView;
		
		moduleModel.deleteModule(moduleFromView);
		
		// TODO: Return redirect
	}
	
	// Returns all of the modules
	public List<Module> viewModules ()
	{
		if (loginCheck())
		{ 
			// TODO: Unauthorised access message
			return; 
		}
		
		ModuleModel moduleModel = new ModuleModel();
		
		return List<Module> moduleList = moduleModel.getModuleList();
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
	private bool loginCheck ()
	{
		// Return true if incorrectly logged-in
		if (true)
		{ return true; }
		else
		{ return false; }
	}
}