package de.j4rvis.kalendarium.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.fragments.OverviewFragment;
import de.j4rvis.kalendarium.fragments.StatisticFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, mOverviewFragment).commit();
                    getSupportActionBar().setTitle(R.string.title_home);
                    return true;
                case R.id.navigation_statistic:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, mStatisticFragment).commit();
                    getSupportActionBar().setTitle(R.string.title_statistic);
                    return true;
                case R.id.navigation_history:
                    return true;
            }
            return false;
        }

    };
    private OverviewFragment mOverviewFragment;
    private StatisticFragment mStatisticFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mOverviewFragment = new OverviewFragment();
        mStatisticFragment = new StatisticFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.content, mOverviewFragment).commit();
        getSupportActionBar().setTitle(R.string.title_home);
    }

}
