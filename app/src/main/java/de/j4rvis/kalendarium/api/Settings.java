package de.j4rvis.kalendarium.api;

/**
 * Created by j4rvis on 4/7/17.
 */

public class Settings {
    public int id;
    public int year;
    // 0 - 11
    public int month;
    public String name;
    public float available;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAvailable() {
        return available;
    }

    public void setAvailable(float available) {
        this.available = available;
    }
}
