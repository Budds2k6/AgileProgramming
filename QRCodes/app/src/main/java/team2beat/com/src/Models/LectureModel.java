package team2beat.com.src.Models;

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
