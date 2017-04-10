package de.j4rvis.kalendarium.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.j4rvis.kalendarium.R;
import de.j4rvis.kalendarium.api.Settings;

/**
 * Created by j4rvis on 4/10/17.
 */

public class SettingsListViewAdapter extends RecyclerView.Adapter<SettingsListViewAdapter.Holder> {

    private final Context mContext;
    private List<Settings> mSettingsList = new ArrayList<>();

    public SettingsListViewAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<Settings> list) {
        mSettingsList = list;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_line_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        final Settings settings = mSettingsList.get(position);
        holder.monthName.setText(settings.getName());
        holder.monthValue.setText(String.format(Locale.getDefault(), "%.2f", settings.getAvailable()));

        holder.monthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
                alertDialog.setTitle("Monthly available");
                alertDialog.setMessage("Change the value");

                final EditText input = new EditText(mContext);
                input.setText(String.format(Locale.getDefault(), "%.2f", settings.getAvailable()));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                input.setLayoutParams(lp);

                alertDialog.setView(input);

                alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holder.monthValue.setText(input.getText().toString());
                        dialog.dismiss();
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSettingsList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView monthName;
        TextView monthValue;
        ImageButton monthEdit;

        public Holder(View itemView) {
            super(itemView);
            monthName = (TextView) itemView.findViewById(R.id.textViewMonth);
            monthValue = (TextView) itemView.findViewById(R.id.textViewMonthValue);
            monthEdit = (ImageButton) itemView.findViewById(R.id.imageButtonMonthEdit);
        }
    }
}
