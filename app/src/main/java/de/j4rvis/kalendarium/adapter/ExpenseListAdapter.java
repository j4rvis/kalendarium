package de.j4rvis.kalendarium.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.api.models.Expense;

/**
 * Created by mhsw on 24.07.17.
 */

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseListViewHolder> {

    private static final String TAG = ExpenseListAdapter.class.getSimpleName();
    private ArrayList<Expense> expenses;
    private ExpenseClickListener listener;
    public ExpenseListAdapter(List<Expense> allExpenses) {
        this.expenses = (ArrayList<Expense>) allExpenses;
    }

    public void setExpenseClickListener(ExpenseClickListener listener) {
        this.listener = listener;
    }

    public void setExpenses(List<Expense> expenses){
        this.expenses = (ArrayList<Expense>) expenses;
        notifyDataSetChanged();
    }

    @Override
    public ExpenseListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_list_item, parent, false);
        return new ExpenseListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ExpenseListViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        Date date = new Date(expense.date);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");
        holder.historyDate.setText(format.format(date));
//        holder.historyDate.setText(DateUtil.calculateSince(expense.date, mApplicationContext));
        holder.historyValue.setText(expense.value + "€");
        holder.historyCategory.setText(String.valueOf(expense.category));
        holder.setId(expense.id);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public interface ExpenseClickListener {
        void onDeleteClickedListener(Expense expense);
        void onEditClickedListener(Expense expense);
    }

    class ExpenseListViewHolder extends RecyclerView.ViewHolder {

        TextView historyDate;
        TextView historyValue;
        TextView historyCategory;
        ImageButton historyEdit;
        ImageButton historyDelete;
        private int id;

        ExpenseListViewHolder(View itemView) {
            super(itemView);
            historyDate = (TextView) itemView.findViewById(R.id.text_view_history_date);
            historyValue = (TextView) itemView.findViewById(R.id.text_view_history_value);
            historyCategory = (TextView) itemView.findViewById(R.id.text_view_history_category);

            historyEdit = (ImageButton) itemView.findViewById(R.id.button_history_edit);
            historyDelete = (ImageButton) itemView.findViewById(R.id.button_history_delete);

            historyDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null)
                        listener.onDeleteClickedListener(expenses.get(position));
                }
            });
            historyEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null)
                        listener.onEditClickedListener(expenses.get(position));
                }
            });
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
