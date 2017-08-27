package de.j4rvis.kalendarium.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.R2;
import de.j4rvis.kalendarium.activities.ExpenseCreateActivity;
import de.j4rvis.kalendarium.activities.MainActivity;
import de.j4rvis.kalendarium.api.AppDatabase;
import de.j4rvis.kalendarium.api.models.Expense;
import de.j4rvis.kalendarium.api.models.Interval;
import de.j4rvis.kalendarium.util.CalcUtil;

/**
 * Created by j4rvis on 4/7/17.
 */

public class OverviewFragment extends Fragment {
    @BindView(R2.id.textViewDaily)
    TextView daily;

    @BindView(R2.id.textViewWeekly)
    TextView weekly;

    @BindView(R2.id.textViewRemaining)
    TextView remaining;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.overview_fragment, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExpenseCreateActivity.class);
                startActivity(intent);
            }
        });
        ButterKnife.bind(this, view);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        AppDatabase database = AppDatabase.getDatabase(null);

        Interval currentInterval = database.intervalModel().getCurrentInterval(System.currentTimeMillis());
        List<Expense> expensesForInterval = database.expenseModel().getExpensesForInterval(currentInterval.id);

        float sumExpenses = 0;
        for (Expense expense : expensesForInterval) {
            sumExpenses+=expense.value;
        }

        float remainingCapacity = currentInterval.capacity - sumExpenses;

        float dailyCapacity = CalcUtil.calcDailyCapacity(remainingCapacity, currentInterval.getFrom().getTime(), currentInterval.getTo().getTime());
        daily.setText(df.format(dailyCapacity));

        float weeklyCapacity = CalcUtil.calcWeeklyCapacity(remainingCapacity, currentInterval.getFrom().getTime(), currentInterval.getTo().getTime());
        weekly.setText(df.format(weeklyCapacity));

        remaining.setText(df.format(remainingCapacity));

        return view;
    }

    @Override
    public void onResume() {
        View view = super.getView();

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        AppDatabase database = AppDatabase.getDatabase(null);

        Interval currentInterval = database.intervalModel().getCurrentInterval(System.currentTimeMillis());
        List<Expense> expensesForInterval = database.expenseModel().getExpensesForInterval(currentInterval.id);

        float sumExpenses = 0;
        for (Expense expense : expensesForInterval) {
            sumExpenses+=expense.value;
        }

        float remainingCapacity = currentInterval.capacity - sumExpenses;

        float dailyCapacity = CalcUtil.calcDailyCapacity(remainingCapacity, currentInterval.getFrom().getTime(), currentInterval.getTo().getTime());
        daily.setText(df.format(dailyCapacity));

        float weeklyCapacity = CalcUtil.calcWeeklyCapacity(remainingCapacity, currentInterval.getFrom().getTime(), currentInterval.getTo().getTime());
        weekly.setText(df.format(weeklyCapacity));

        remaining.setText(df.format(remainingCapacity));

        super.onResume();
    }
}
