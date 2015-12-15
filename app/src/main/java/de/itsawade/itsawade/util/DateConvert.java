package de.itsawade.itsawade.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hendrik on 15.12.15.
 */
public class DateConvert {

    public String DateConvert(String dat) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d");
        String a = "";
        try {
            Date date = format.parse(dat);
            a = format.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return a;

    }
}
