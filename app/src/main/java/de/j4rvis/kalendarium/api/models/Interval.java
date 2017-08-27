package de.j4rvis.kalendarium.api.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by mhsw on 24.07.17.
 */

@Entity
public class Interval {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "interval_from")
    public long from;
    @ColumnInfo(name = "interval_to")
    public long to;
    public float capacity;

    public Interval(int id, long from, long to, float capacity) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    @Ignore
    public Interval(long from, long to, float capacity) {
        this(0, from, to, capacity);
    }

    public Calendar getFrom() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date(from));
        return cal;
    }

    public Calendar getTo() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date(to));
        return cal;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", capacity=" + capacity +
                '}';
    }
}
