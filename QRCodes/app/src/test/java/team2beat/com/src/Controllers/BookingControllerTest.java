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
    public void testAddStudentValid ()
    {
        BookingController bc = new BookingController();
        PresentRecord pr = new PresentRecord(257, "080003474");

        boolean returnedValue = bc.mockSetAttendance(pr);
        assertTrue("Fail! - Student should have been added; valid entry!", returnedValue);

    }

    @Test
// Lecturer adding student manually
    public void testAddStudentInvalid()
    {
        BookingController bc = new BookingController();
        PresentRecord wrongPr = new PresentRecord(257, "0493828x5");


        // Test invalid studentID
        boolean returnedValue = bc.mockSetAttendance(wrongPr);
        assertFalse("Fail! - Invalid student should not have been added; invalid ID!", returnedValue);



    }


    @Test
// Lecturer adding student manually
    public void testAddStudentAlreadySigned ()
    {
        BookingController bc = new BookingController();
        PresentRecord prAlreadySigned = new PresentRecord(257,"130005440");


        // Test valid student ID, and is already registered
        boolean returnedValue = bc.mockSetAttendance(prAlreadySigned);
        assertFalse("Fail! - Student should not have been added; already on list!", returnedValue);


    }






}