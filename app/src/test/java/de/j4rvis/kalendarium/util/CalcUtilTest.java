package de.j4rvis.kalendarium.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by mhsw on 23.08.17.
 */

public class CalcUtilTest {

    @Test
    public void calDailyCapacity(){
        IntervalUtil.DateInterval dateInterval = IntervalUtil.getCurrentDateInterval(true);
        double capacity = CalcUtil.calcDailyCapacity(310, dateInterval.begin, dateInterval.end);
        assertEquals(10, capacity, 0.1);
    }

    @Test
    public void calWeeklyCapacity(){
        IntervalUtil.DateInterval dateInterval = IntervalUtil.getCurrentDateInterval(true);
        double capacity = CalcUtil.calcWeeklyCapacity(310, dateInterval.begin, dateInterval.end);
        assertEquals(70, capacity, 0.1);
    }
}
