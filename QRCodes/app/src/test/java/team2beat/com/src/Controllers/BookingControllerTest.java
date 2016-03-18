package team2beat.com.src.Controllers;

import junit.framework.TestCase;

import org.junit.Test;

import team2beat.com.qrcodes.PresentRecord;

/**
 * Created by Lewis on 16/03/2016.
 */
public class BookingControllerTest extends TestCase
{

    @Test
    public void testAddStudentFunctions()
    {
        BookingController bc = new BookingController();
        PresentRecord pr = new PresentRecord(257, "080003474");
        PresentRecord wrongPr = new PresentRecord(257, "0493828x5");
        PresentRecord prAlreadySigned = new PresentRecord(257,"130005440");

        //Test valid entry
        assertTrue("Fail! - Student should have been added; valid entry!", bc.setStudentAttendance(pr));

        //Test invalid studentID
        assertFalse("Fail! - Invalid student should not have been added; invalid ID!", bc.setStudentAttendance(wrongPr));

        //Test valid student ID, and is already registered
        assertFalse("Fail! - Student should not have been added; already on list!", bc.setStudentAttendance(prAlreadySigned));
    }
}