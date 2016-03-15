package team2beat.com.src.DataObjects;

public class Module
{
	private String moduleID;
	private String name;
	private String coordinator;
	
	// Constructor
	public Module (String moduleID, String name, String coordinator)
	{
		this.moduleID = moduleID;
		this.name = name;
		this.coordinator = coordinator;
	}

	// Constructor
	public Module (String moduleID, String moduleName)
	{
		this.moduleID = moduleID;
		this.name = name;
	}

	public String getModuleName(){ return this.name; }

}
