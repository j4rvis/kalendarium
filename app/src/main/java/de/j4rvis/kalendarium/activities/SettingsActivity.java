package de.j4rvis.kalendarium.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.adapter.SettingsListViewAdapter;

/**
 * Created by j4rvis on 4/8/17.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private ImageButton mImageButton;
    private RecyclerView mRecyclerView;
    private SettingsListViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditText = (EditText) findViewById(R.id.editTextMonthValue);
        mImageButton = (ImageButton) findViewById(R.id.imageButtonMonthAdd);
        mRecyclerView = (RecyclerView) findViewById(R.id.listViewMonth);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new SettingsListViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

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

    @Override
    public void onClick(View v) {
    }
}
