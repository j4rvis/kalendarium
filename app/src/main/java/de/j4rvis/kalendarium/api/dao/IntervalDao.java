package de.j4rvis.kalendarium.api.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import de.j4rvis.kalendarium.api.models.Interval;

/**
 * Created by mhsw on 24.07.17.
 */
@Dao
public interface IntervalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addInterval(Interval interval);

    @Query("select * from interval")
    List<Interval> getAllIntervals();

    @Query("select * from interval where id = :id")
    Interval getInterval(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateInterval(Interval interval);

    @Query("delete from interval")
    void removeAllIntervals();

    @Query("select * from interval where interval_from <= :now and interval_to > :now")
    Interval getCurrentInterval(long now);
}
