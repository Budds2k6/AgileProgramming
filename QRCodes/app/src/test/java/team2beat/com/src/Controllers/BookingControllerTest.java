package team2beat.com.src.Controllers;

import junit.framework.TestCase;

import org.junit.Test;

import team2beat.com.qrcodes.PresentRecord;

/**
 * Created by Lewis on 16/03/2016.
 */
public class BookingControllerTest extends TestCase {


    @Test
// Lecturer adding student manually
    public void testAddStudent ()
    {
        BookingController bc = new BookingController();
        PresentRecord pr = new PresentRecord(257, "080003474");
        PresentRecord wrongPr = new PresentRecord(257, "049382839");


        // Test invalid studentID
        //assertFalse("Fail! - Invalid student should not have been added; invalid ID!", bc.setAttendance(wrongPr));

        // Test valid studentID, and not already registered
        //assertTrue("Fail! - Student should have been added; valid entry!", bc.setAttendance(pr));

        // Test valid student ID, and is already registered
        //assertFalse("Fail! - Student should not have been added; already on list!", bc.setAttendance(pr));
    }

    @Test
// Lecturer adding student manually
    public void testAddStudentOne ()
    {
        BookingController bc = new BookingController();
        PresentRecord pr = new PresentRecord(257, "080003474");
        PresentRecord wrongPr = new PresentRecord(257, "049382839");

        // Test invalid studentID
        //assertFalse("Fail! - Invalid student should not have been added; invalid ID!", bc.setAttendance(wrongPr));


        // Test valid studentID, and not already registered


        boolean returnedValue = bc.setAttendance(pr);
        assertTrue("Fail! - Student should have been added; valid entry!", returnedValue);

        // Test valid student ID, and is already registered
        //assertFalse("Fail! - Student should not have been added; already on list!", bc.setAttendance(pr));
    }


    @Test
// Lecturer adding student manually
    public void testAddStudentTwo ()
    {
        BookingController bc = new BookingController();
        PresentRecord pr = new PresentRecord(257, "080003474");
        PresentRecord wrongPr = new PresentRecord(257, "049382839");

        // Test invalid studentID
        //assertFalse("Fail! - Invalid student should not have been added; invalid ID!", bc.setAttendance(wrongPr));

        // Test valid studentID, and not already registered
        //assertTrue("Fail! - Student should have been added; valid entry!", bc.setAttendance(pr));



        // Test valid student ID, and is already registered
        //assertFalse("Fail! - Student should not have been added; already on list!", bc.setAttendance(pr));
    }






}