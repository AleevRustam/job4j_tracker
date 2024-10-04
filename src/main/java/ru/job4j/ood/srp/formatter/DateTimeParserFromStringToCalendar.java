package ru.job4j.ood.srp.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeParserFromStringToCalendar {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public static Calendar parse(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DATE_FORMAT.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
