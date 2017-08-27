package de.j4rvis.kalendarium.util;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import de.j4rvis.kalendarium.R;

/**
 * Created by mhsw on 24.07.17.
 */

public class DateUtil {
    public static double SECOND = 1000;
    public static double MINUTE = SECOND*60;
    public static double HOUR = MINUTE*60;
    public static double DAY = HOUR*24;
    public static double WEEK = DAY*7;

    // TODO remove context -> return since as seconds. UI should care for unit
    public static String calculateSince(long timestamp, Context context){
        long difference = System.currentTimeMillis() - timestamp;
        if(difference <= 0) return "0";
        double value;
        int unit = 0;

        if((value = difference/MINUTE) < 60){
            unit = value <= 1 ? R.string.minute : R.string.minutes;
            if(value<1) return "< 1 " + context.getResources().getString(unit);
        } else if((value = difference/HOUR) < 24){
            unit = value == 1 ? R.string.hour : R.string.hours;
        } else if((value = difference/DAY) < 7){
            unit = value == 1 ? R.string.day : R.string.days;
        }
        return ((long) value) + context.getResources().getString(unit);
    }
}
