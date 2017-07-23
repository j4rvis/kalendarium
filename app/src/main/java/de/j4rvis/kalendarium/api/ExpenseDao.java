package de.j4rvis.kalendarium.api;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by mhsw on 22.07.17.
 */

@Dao
public interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addExpense(Expense expense);

    @Query("select * from expense")
    List<Expense> getAllExpenses();

    @Query("select * from expense where id = :id")
    List<Expense> getExpense(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateExpense(Expense expense);

    @Query("delete from expense")
    void removeAllExpenses();
}
