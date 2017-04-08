package de.j4rvis.kalendarium.api;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by j4rvis on 4/7/17.
 */

public class Settings extends RealmObject{
    @PrimaryKey
    public int id;
    public int year;
    // 0 - 11
    public int month;
    public float available;
}
