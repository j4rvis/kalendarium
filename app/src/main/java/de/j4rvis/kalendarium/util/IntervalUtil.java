package de.j4rvis.kalendarium.util;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.j4rvis.kalendarium.api.AppDatabase;
import de.j4rvis.kalendarium.api.models.Interval;

/**
 * Created by mhsw on 20.08.17.
 */

public class IntervalUtil {

    private final static String TAG = IntervalUtil.class.getSimpleName();
    private final AppDatabase database;

    private boolean startOfMonth = false;
    private float capacity = 500;

    public IntervalUtil(Context context) {
        database = AppDatabase.getDatabase(context);
    }

    public void setupInterval(){
        // do we have an interval ?
        List<Interval> allIntervals = database.intervalModel().getAllIntervals();
        if(allIntervals.isEmpty()){
            // Okay we don't have any intervals
            // so get the settings like starting interval begin beginning of month or begin the mid
            // TODO we'll start begin the mid, 16th of the month
            DateInterval dateInterval = getCurrentDateInterval(false);
            Log.d(TAG, "current: " + dateInterval);
            List<DateInterval> nextThreeDateIntervals = getNextThreeDateIntervals(dateInterval);
            Log.d(TAG, "nextThree: " + nextThreeDateIntervals.toString());

            database.intervalModel().addInterval(new Interval(dateInterval.begin.getTime(), dateInterval.end.getTime(), capacity));

            for (DateInterval interval : nextThreeDateIntervals) {
                database.intervalModel().addInterval(new Interval(interval.begin.getTime(), interval.end.getTime(), capacity));
            }
        } else {
            // Haben wir den derzeitigen Interval?
            Interval currentInterval = database.intervalModel().getCurrentInterval(System.currentTimeMillis());
            if (currentInterval==null){
                // nein? dann erzeuge ihn plus die näachsten drei
                DateInterval dateInterval = getCurrentDateInterval(false);
                Log.d(TAG, "current: " + dateInterval);
                List<DateInterval> nextThreeDateIntervals = getNextThreeDateIntervals(dateInterval);
                Log.d(TAG, "nextThree: " + nextThreeDateIntervals.toString());

                database.intervalModel().addInterval(new Interval(dateInterval.begin.getTime(), dateInterval.end.getTime(), capacity));

                for (DateInterval interval : nextThreeDateIntervals) {
                    database.intervalModel().addInterval(new Interval(interval.begin.getTime(), interval.end.getTime(), capacity));
                }
            } else {
                // ja? dann überprüfe ob wir die nächsten drei haben
            }
        }
        DateInterval dateInterval = getCurrentDateInterval(false);
        Log.d(TAG, "current: " + dateInterval);
        List<DateInterval> nextThreeDateIntervals = getNextThreeDateIntervals(dateInterval);
        Log.d(TAG, "nextThree: " + nextThreeDateIntervals.toString());
    }

    private List<DateInterval> getNextThreeDateIntervals(DateInterval current){
        ArrayList<DateInterval> list = new ArrayList<>();
        DateInterval last = current;
        for (int i = 0; i < 3; i++){
            last = getNextDateInterval(last);
            list.add(last);
        }
        return list;
    }

    public static DateInterval getCurrentDateInterval(boolean beginOfMonth){
        Calendar calendar = getLatestBegin(beginOfMonth);
        Date begin = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        Date end = calendar.getTime();

        return new DateInterval(begin, end);
    }

    private DateInterval getNextDateInterval(DateInterval current){
        Calendar currentBegin = new GregorianCalendar();
        currentBegin.setTime(current.begin);
        GregorianCalendar currentEnd = new GregorianCalendar();
        currentEnd.setTime(current.end);
        currentBegin.add(Calendar.MONTH, 1);
        currentEnd.add(Calendar.MONTH, 1);
        return new DateInterval(currentBegin.getTime(), currentEnd.getTime());
    }

    private static Calendar getLatestBegin(boolean beginOfMonth){
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (beginOfMonth){
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        } else {
            if (day <= 15) {
                calendar.add(Calendar.MONTH, -1);
            }
            calendar.set(Calendar.DAY_OF_MONTH, 16);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static class DateInterval{
        Date begin;
        Date end;

        public DateInterval(Date begin, Date end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            return "DateInterval{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }

        public boolean equalsInterval(Interval interval){
            Date intervalBegin = new Date(interval.from);
            Date intervalEnd = new Date(interval.to);

            return (intervalBegin.equals(this.begin) && intervalEnd.equals(this.end));
        }
    }

}
