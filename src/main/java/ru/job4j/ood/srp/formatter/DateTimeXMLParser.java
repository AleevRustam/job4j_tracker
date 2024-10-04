package ru.job4j.ood.srp.formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeXMLParser extends XmlAdapter<String, Calendar> {

    @Override
    public Calendar unmarshal(String v) throws Exception {
        return DateTimeParserFromStringToCalendar.parse(v);
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        return dateFormat.format(v.getTime());
    }
}
