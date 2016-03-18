package team2beat.com.src.Models;

import java.util.Comparator;
import java.util.Date;

import team2beat.com.src.DataObjects.ShouldAttend;

public class CustomComparator implements Comparator<ShouldAttend>{
    @Override
    public int compare(ShouldAttend s1, ShouldAttend s2) {

        String surname1, surname2;
        int index = s1.getStudentName().indexOf(' ');
        surname1 = s1.getStudentName().substring(index + 1);

        index = s2.getStudentName().indexOf(' ');
        surname2 = s2.getStudentName().substring(index + 1);

        return surname1.compareTo(surname2);

    }
}