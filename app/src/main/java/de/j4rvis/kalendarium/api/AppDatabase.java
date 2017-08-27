package de.j4rvis.kalendarium.api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import de.j4rvis.kalendarium.api.dao.ExpenseDao;
import de.j4rvis.kalendarium.api.dao.IntervalDao;
import de.j4rvis.kalendarium.api.models.Expense;
import de.j4rvis.kalendarium.api.models.Interval;

/**
 * Created by mhsw on 22.07.17.
 */

@Database(entities = { Expense.class, Interval.class }, version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ExpenseDao expenseModel();

    public abstract IntervalDao intervalModel();

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "kalendarium01")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
