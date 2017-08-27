package de.j4rvis.kalendarium.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import de.j4rvis.kalendarium.api.models.Expense;

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
    Expense getExpense(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateExpense(Expense expense);

    @Query("select * from expense where category like :category")
    List<Expense> getExpensesForCategory(String category);

    @Query("select * from expense where interval_id like :interval")
    List<Expense> getExpensesForInterval(int interval);

    @Query("delete from expense")
    void removeAllExpenses();

    @Query("delete from expense where id like :expense_id")
    void removeExpense(int expense_id);
}
