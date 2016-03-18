package team2beat.com.src.DataObjects;

import android.os.Parcel;
import android.os.Parcelable;

public class Module implements Parcelable
{
	private String moduleID;
	private String name;
	private String coordinator;
	private String coordinatorName;

	//region PARCELABLE NECESSITIES
	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags)
	{
		out.writeInt(0);
	}

	public static final Parcelable.Creator<Module> CREATOR
			= new Parcelable.Creator<Module>() {
		public Module createFromParcel(Parcel in) {
			return new Module(in);
		}

		public Module[] newArray(int size) {
			return new Module[size];
		}
	};

	private Module(Parcel in) {
	}

	//endregion

	// Constructor
	public Module (String moduleID, String name, String coordinator, String fName, String sName)
	{
		this.moduleID = moduleID;
		this.name = name;
		this.coordinator = coordinator;
		this.coordinatorName = fName + " " + sName;
	}

	// Constructor
	public Module (String moduleID, String moduleName)
	{
		this.moduleID = moduleID;
		this.name = name;
	}

	public String getModuleName(){ return this.name; }
	public String getModuleCode(){ return this.moduleID; }
	public String getCoordinatorName(){ return this.coordinatorName; }
	public String getCoordinator(){ return this.coordinator; }

}
