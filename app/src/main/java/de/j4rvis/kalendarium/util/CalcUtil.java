package de.j4rvis.kalendarium.util;

import java.util.Date;

import de.j4rvis.kalendarium.api.models.Interval;

/**
 * Created by mhsw on 23.08.17.
 */

public class CalcUtil {

    public static float calcDailyCapacity(float capacity, Date from, Date to) {
        long diff = to.getTime() - from.getTime();
        float days = Math.round(diff / DateUtil.DAY);
        return capacity / days;
    }

    public static float calcDailyCapacity(Interval interval) {
        return CalcUtil.calcDailyCapacity(interval.capacity, interval.getFrom().getTime(), interval.getTo().getTime());
    }

    public static float calcWeeklyCapacity(float capacity, Date from, Date to) {
        long diff = to.getTime() - from.getTime();
        float days = Math.round(diff / DateUtil.DAY);
        float weeks = days / 7;
        return capacity / weeks;
    }
}
