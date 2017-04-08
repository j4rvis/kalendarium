package de.j4rvis.kalendarium.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.api.Settings;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by j4rvis on 4/8/17.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private ImageButton mImageButton;
    private RecyclerView mRecyclerView;
    private Realm mRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRealm = Realm.getDefaultInstance();
        mEditText = (EditText) findViewById(R.id.editTextMonthValue);
        mImageButton = (ImageButton) findViewById(R.id.imageButtonMonthAdd);
        mRecyclerView = (RecyclerView) findViewById(R.id.listViewMonth);

        mImageButton.setOnClickListener(this);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private int getNextKey() {
        try {
            return mRealm.where(Settings.class).max("id").intValue() + 1;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e ) {
            return 0;
        }
    }

    @Override
    public void onClick(View v) {
        if (mEditText.getText().length() > 0) {
            // Check if we already have some months in realm
            RealmQuery<Settings> query = mRealm.where(Settings.class);
            RealmResults<Settings> all = query.findAll();
            Calendar calendar = Calendar.getInstance();
            int year = 0;
            int month = 0;
            if (all.size() == 0) {
                // We do not have any settings saved
                calendar.setTime(new Date());
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);
            } else {
                // get the last month
                RealmResults<Settings> allSorted = query.findAllSorted("id");
                Settings last = allSorted.last();
                if (last.month == 11) {
                    year = last.year + 1;
                    month = 0;
                } else {
                    year = last.year;
                    month = last.month + 1;
                }
                calendar.set(year, month, 1);
            }
            String displayName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
            Float available = Float.valueOf(mEditText.getText().toString());

            mRealm.beginTransaction();
            Settings settings = mRealm.createObject(Settings.class, getNextKey());
            settings.setMonth(month);
            settings.setYear(year);
            settings.setName(displayName);
            settings.setAvailable(available);
            mRealm.commitTransaction();
        }
    }
}
