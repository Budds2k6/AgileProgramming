package team2beat.com.src.DataObjects;

//Refactored - created along with LectureModel to store details about the lecture

public class Lecture 
{
	private String classID;
	private String moduleID;
	public enum LectType{Lab, Tutorial, Lecture, Extra, Other};
	private LectType lType;

	// Class Constructor
	public Lecture(String classID, String moduleID, LectType lType)
	{
		this.classID = classID;
		this.moduleID = moduleID;
		this.lType = lType;
	}
}
