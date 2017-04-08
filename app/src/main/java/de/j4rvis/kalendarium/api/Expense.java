package de.j4rvis.kalendarium.api;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by j4rvis on 4/7/17.
 */

public class Expense extends RealmObject {
    @PrimaryKey
    public int id;
    public float value;
    public long date;
    public Category category;
}
