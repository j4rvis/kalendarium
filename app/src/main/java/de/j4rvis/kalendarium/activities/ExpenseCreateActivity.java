package de.j4rvis.kalendarium.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.R2;
import de.j4rvis.kalendarium.api.AppDatabase;
import de.j4rvis.kalendarium.api.models.Expense;
import de.j4rvis.kalendarium.api.models.Interval;

/**
 * Created by j4rvis on 4/7/17.
 */

public class ExpenseCreateActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R2.id.edit_text_expense) EditText editText;
    @BindView(R2.id.button_topic_activities) Button activities;
    @BindView(R2.id.button_topic_cosmetics) Button cosmetics;
    @BindView(R2.id.button_topic_household) Button household;
    @BindView(R2.id.button_topic_food) Button food;
    @BindView(R2.id.button_topic_games) Button games;
    @BindView(R2.id.button_topic_shopping) Button shopping;
    @BindView(R2.id.button_topic_plus) Button plus;

    @BindView(R2.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_create_activity);

        ButterKnife.bind(this);

        activities.setOnClickListener(this);
        cosmetics.setOnClickListener(this);
        household.setOnClickListener(this);
        food.setOnClickListener(this);
        games.setOnClickListener(this);
        shopping.setOnClickListener(this);
        plus.setOnClickListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        String valueString = editText.getText().toString();
        if(valueString.isEmpty()) {
            Toast.makeText(this, "Der Wert sollte nicht leer sein", Toast.LENGTH_SHORT).show();
            return;
        }
        float value = Float.parseFloat(valueString);

        String category = "";
        switch (v.getId()){
            case R.id.button_topic_activities:
                category = "activities";
                break;
            case R.id.button_topic_cosmetics:
                category = "cosmetics";
                break;
            case R.id.button_topic_household:
                category = "household";
                break;
            case R.id.button_topic_food:
                category = "food";
                break;
            case R.id.button_topic_games:
                category = "games";
                break;
            case R.id.button_topic_shopping:
                category = "shopping";
                break;
            case R.id.button_topic_plus:
                category = "plus";
                break;
        }

        AppDatabase database = AppDatabase.getDatabase(null);
        Interval currentInterval = database.intervalModel().getCurrentInterval(System.currentTimeMillis());
        Expense expense = Expense.builder().setInterval(currentInterval.id)
                .setDate(System.currentTimeMillis())
                .setCategory(category)
                .setValue(value).build();

        database.expenseModel().addExpense(expense);

        this.finish();
    }
}
