package de.j4rvis.kalendarium.api;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by j4rvis on 4/7/17.
 */

@Entity
public class Expense {
    @PrimaryKey
    public int id;
    public float value;
    public int category;
    public long date;

    public Expense(int id, float value, int category) {
        this.id = id;
        this.value = value;
        this.category = category;
//        this.date = date;
    }

    public static ExpenseBuilder builder(){
        return new ExpenseBuilder();
    }

    public static class ExpenseBuilder{
        private int id;
        private float value;
        private Date date;
        private int category;

        public ExpenseBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ExpenseBuilder setValue(float value) {
            this.value = value;
            return this;
        }

        public ExpenseBuilder setDate(Date date) {
            this.date = new Date(date.getTime());
            return this;
        }

        public ExpenseBuilder setCategory(int category) {
            this.category = category;
            return this;
        }

        public Expense build(){
            return new Expense(id, value, category);
        }
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", value=" + value +
                ", category=" + category +
                '}';
    }
}



