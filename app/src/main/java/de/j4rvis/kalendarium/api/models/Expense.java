package de.j4rvis.kalendarium.api.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by j4rvis on 4/7/17.
 */

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public float value;
    public long date;
    public String category;

    @ColumnInfo(name = "interval_id")
    public int intervalId;

    public Expense(int id, float value, long date, String category, int intervalId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.category = category;
        this.intervalId = intervalId;
    }

    public static ExpenseBuilder builder(){
        return new ExpenseBuilder();
    }

    public static class ExpenseBuilder{
        private float value;
        private long date;
        private String category;
        private int intervalId;

        public ExpenseBuilder setValue(float value) {
            this.value = value;
            return this;
        }

        public ExpenseBuilder setDate(long date) {
            this.date = date;
            return this;
        }

        public ExpenseBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public ExpenseBuilder setInterval(int intervalId) {
            this.intervalId = intervalId;
            return this;
        }

        public Expense build(){
            return new Expense(0, value, date, category, intervalId);
        }
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", value=" + value +
                ", date=" + date +
                ", intervalId=" + intervalId +
                ", category=" + category+
                '}';
    }
}



