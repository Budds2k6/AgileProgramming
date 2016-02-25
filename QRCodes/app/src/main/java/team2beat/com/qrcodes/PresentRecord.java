package team2beat.com.qrcodes;

/**
 * Created by asuth on 23/02/2016.
 */
public class PresentRecord
{
    public int moduleID;
    public String studentID;
    public boolean present;

    public PresentRecord(int module, String student)
    {
        moduleID = module;
        studentID = student;
        present = true;
    }
}
