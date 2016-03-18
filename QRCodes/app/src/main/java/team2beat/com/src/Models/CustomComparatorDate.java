package team2beat.com.src.Models;

import java.util.Comparator;
import java.util.Date;

import team2beat.com.src.DataObjects.Booking;
import team2beat.com.src.DataObjects.ShouldAttend;

public class CustomComparatorDate implements Comparator<Booking>{
    @Override
    public int compare(Booking b1, Booking b2) {

        return b1.getDate().compareTo(b2.getDate());

    }
}