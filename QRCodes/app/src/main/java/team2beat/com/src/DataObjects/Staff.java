package team2beat.com.src.DataObjects;

public class Staff
{
	private String staffID;
	private String jobID;

	// Constructor
	Staff(String staffID, String jobID)
	{
		this.staffID = staffID;
		this.jobID = jobID;
	}

	//Accessor methods
	public String getStaffID()
	{
		return staffID;
	}

	public String getJobID()
	{
		return jobID;
	}
}
