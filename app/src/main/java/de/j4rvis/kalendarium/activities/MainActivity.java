package de.j4rvis.kalendarium.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.api.AppDatabase;
import de.j4rvis.kalendarium.fragments.ExpenseListFragment;
import de.j4rvis.kalendarium.fragments.OverviewFragment;
import de.j4rvis.kalendarium.fragments.StatisticFragment;
import de.j4rvis.kalendarium.util.IntervalUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, mOverviewFragment).commit();
                    getSupportActionBar().setTitle(R.string.title_home);
                    return true;
//                case R.id.navigation_statistic:
//                    getSupportFragmentManager().beginTransaction().replace(R.id.content, mStatisticFragment).commit();
//                    getSupportActionBar().setTitle(R.string.title_statistic);
//                    return true;
                case R.id.navigation_expense_list:
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, mExpenseListFragment).commit();
                    getSupportActionBar().setTitle(R.string.title_expense_list);
                    return true;
            }
            return false;
        }

    };

    private OverviewFragment mOverviewFragment;
//    private StatisticFragment mStatisticFragment;
    private ExpenseListFragment mExpenseListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configureLayout();

        IntervalUtil intervalUtil = new IntervalUtil(getApplicationContext());
        intervalUtil.setupInterval();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    private void configureLayout(){
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mOverviewFragment = new OverviewFragment();
//        mStatisticFragment = new StatisticFragment();
        mExpenseListFragment = new ExpenseListFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.content, mOverviewFragment).commit();
        getSupportActionBar().setTitle(R.string.title_home);

    }
}
