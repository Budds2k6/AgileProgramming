package team2beat.com.src.Models;


import java.util.List;

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

}
