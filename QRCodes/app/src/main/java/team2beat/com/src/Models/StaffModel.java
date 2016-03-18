
package team2beat.com.src.Models;

import java.util.ArrayList;
import java.util.List;

import team2beat.com.src.AsyncClasses.StaffAsync;
import team2beat.com.src.DataObjects.Module;

public class StaffModel
{
	private String jobID;
	private ArrayList<String[]> returnData;

	// Constructor
	public StaffModel()
	{}

	public void createStaff(StaffModel sm){}
	public void deleteStaff(String id){}
	public List<StaffModel> getStaffList()
	{
		return null;
	}

	public ArrayList<Module> getAllModules(String staffID, String accLev)
	{

		StaffAsync sa = new StaffAsync(staffID, accLev);

		while (!sa.complete) {
			returnData = sa.toReturn;
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//create modules out of the data
		ArrayList<Module> moduleList = new ArrayList<Module>();

		for(int i = 0; i < returnData.size(); i++)
		{
			Module m = new Module(returnData.get(i)[0], returnData.get(i)[1], returnData.get(i)[2], returnData.get(i)[3], returnData.get(i)[4]);
			moduleList.add(m);
		}

		return moduleList;


	}

}
