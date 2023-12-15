package com.dlt.practice.coding_challenges;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class OneHundredDays {
    public static void main(String[] args) {
        // Old way
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(GregorianCalendar.DAY_OF_YEAR, 100);
        Locale loc = Locale.UK;
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
        System.out.println(dateFormat.format(cal.getTime()));

        // From Java 8
        Period hundredDays = Period.ofDays(100);
        LocalDate today = LocalDate.now(ZoneId.of("Europe/London"));
        LocalDate hundredDaysFromToday = today.plus(hundredDays);
        System.out.println(hundredDaysFromToday);
        
    }
}
