package team2beat.com.src.DataObjects;

public class Lecture 
{
	private String classID;
	private String moduleID;
	public enum LectType{Lab, Tutorial, Lecture, Extra, Other};
	private LectType theType;

	// Class Constructor
	Lecture()
	{}
}
