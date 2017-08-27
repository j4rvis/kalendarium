package de.j4rvis.kalendarium.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.List;

import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.adapter.ExpenseListAdapter;
import de.j4rvis.kalendarium.api.AppDatabase;
import de.j4rvis.kalendarium.api.models.Expense;

/**
 * Created by mhsw on 24.07.17.
 */

public class ExpenseListFragment extends Fragment implements ExpenseListAdapter.ExpenseClickListener {

    private static final String TAG = ExpenseListFragment.class.getSimpleName();
    private AppDatabase mDatabase;
    private ExpenseListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.expense_list_fragment, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mDatabase = AppDatabase.getDatabase(getActivity().getApplicationContext());
        List<Expense> allExpenses = mDatabase.expenseModel().getAllExpenses();

        // specify an adapter (see also next example)
        mAdapter = new ExpenseListAdapter(allExpenses);
        mAdapter.setExpenseClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    @Override
    public void onDeleteClickedListener(final Expense expense) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ausgabe löschen")
                .setMessage("Soll die Ausgabe gelöscht werden?")
                .setPositiveButton("Löschen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabase.expenseModel().removeExpense(expense.id);
                        mAdapter.setExpenses(mDatabase.expenseModel().getAllExpenses());
                    }
                })
                .setNegativeButton("Abbrechen", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onEditClickedListener(final Expense expense) {
        final EditText input = new EditText(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setText(String.valueOf(expense.value));
//        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ausgabenwert bearbeiten")
                .setView(input)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = input.getText().toString();
                        if(s.isEmpty()){
                            Toast.makeText(getActivity(), "Der neue Wert darf nicht leer sein", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        float v = Float.parseFloat(s);
                        expense.value = v;
                        mDatabase.expenseModel().updateExpense(expense);
                        mAdapter.setExpenses(mDatabase.expenseModel().getAllExpenses());
                    }
                })
                .setNegativeButton("Abbrechen", null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        input.requestFocus();
    }
}
