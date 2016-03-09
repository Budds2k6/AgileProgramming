package team2beat.com.src.DataObjects;

public class Location {
	private String locationID;
	private String roomNumber;
	private String buildingName;
	private int capacity;

	// Class Constructor
	public Location(String locationID, String roomNumber, String buildingName, int capacity)
	{
		this.locationID = locationID;
		this.roomNumber = roomNumber;
		this.buildingName = buildingName;
		this.capacity = capacity;
	}

	public Location (String locationID, String roomNumber, String buildingName)
	{
		this.locationID = locationID;
		this.roomNumber = roomNumber;
		this.buildingName = buildingName;
	}
}
