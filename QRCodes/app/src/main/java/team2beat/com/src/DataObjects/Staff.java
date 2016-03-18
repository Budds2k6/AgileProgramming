package team2beat.com.src.DataObjects;

public class Staff extends User
{
	private String staffID;
	private int jobID;
	private int accessLevel;

	// Constructor
	public Staff(String staffID, String username,String firstname, String surname)
	{
		super(username,firstname,surname);

		this.staffID = staffID;
		//this.jobID = jobID;
	}

	public void setJob(int jID, int acc)
	{
		jobID = jID;
		accessLevel = acc;
	}

	//Accessor methods
	public String getStaffID()
	{
		return staffID;
	}

	/*public String getJobID()
	{
		return jobID;
	}*/
}
