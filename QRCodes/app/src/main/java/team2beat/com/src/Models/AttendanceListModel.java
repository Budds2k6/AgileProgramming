package team2beat.com.src.Models;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import team2beat.com.src.AsyncClasses.TodaysClassesAsync;
import team2beat.com.src.AsyncClasses.ViewRegisterAsync;
import team2beat.com.src.DataObjects.Attendee;
import team2beat.com.src.DataObjects.Booking;

public class AttendanceListModel {
    private String attListID;
    ArrayList<String[]> returnedAttendees;

    // Class Constructor
    public AttendanceListModel() {
    }

    public ArrayList<Attendee> getRegisterByID(int attendanceID) {
        try {

            ViewRegisterAsync vra = new ViewRegisterAsync(attendanceID);

            while (!vra.complete) {
                returnedAttendees = vra.toReturn;
            }


            ArrayList<Attendee> attendeeList = new ArrayList<Attendee>();

            for (int i = 0; i < returnedAttendees.size(); i++) {

                String[] current = returnedAttendees.get(i);

                String studentID = current[2];
                String present = current[4];
                String exception = current[3];
                String name = current[0] + current[1];


                Attendee attendee = new Attendee(name, studentID, String.valueOf(attendanceID), exception, Boolean.valueOf(present));
                attendeeList.add(attendee);
            }

            return attendeeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}


// module list (all modules)