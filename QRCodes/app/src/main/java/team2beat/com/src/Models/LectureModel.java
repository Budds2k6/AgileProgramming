package team2beat.com.src.Models;

//Refactoring - LectureModel's details were formerly in BookingModel - it now has its own class
public class LectureModel
{
	private String classID;
	private String moduleID;
	public enum LectType{Lab, Tutorial, Lecture, Extra, Other};
	private LectType theType;

	// Class Constructor
	LectureModel()
	{}
}
