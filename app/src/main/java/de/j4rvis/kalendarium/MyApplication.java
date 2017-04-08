package de.j4rvis.kalendarium;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by j4rvis on 4/7/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
