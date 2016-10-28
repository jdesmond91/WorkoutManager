package com.example.amandajonathan.workoutmanager;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Amanda on 10/28/2016.
 */
public class EditListAdapter extends BaseAdapter {

    public Double reps[];
    public Double weights[];
    private String[] exerciseNames;

    private Context context;
    CustomButtonListener customButtonListener;

    public class EditListViewHolder {

        TextView exerciseName ;
        Button addReps;
        EditText reps ;
        Button subtractReps;
        EditText weight;
    }

    public EditListAdapter(Context context, String[] exerciseNames) {
        this.context = context;
        this.exerciseNames = exerciseNames;
        reps = new Double[exerciseNames.length];
        weights = new Double[exerciseNames.length];
    }

    public void setCustomButtonListener(CustomButtonListener customButtonListner)
    {
        this.customButtonListener = customButtonListner;
    }

    @Override
    public int getCount() {
        return exerciseNames.length;
    }

    @Override
    public String getItem(int position) {
        return exerciseNames[position];
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        final EditListViewHolder listViewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.edit_exercise_listview, parent, false);
            listViewHolder = new EditListViewHolder();
            listViewHolder.exerciseName = (TextView) row.findViewById(R.id.exerciseName);
            listViewHolder.addReps = (Button) row.findViewById(R.id.addReps);
            listViewHolder.reps = (EditText) row.findViewById(R.id.reps);
            listViewHolder.subtractReps = (Button) row.findViewById(R.id.subtractReps);
            listViewHolder.weight = (EditText) row.findViewById(R.id.weight);
            row.setTag(listViewHolder);
        } else {
            row = convertView;
            listViewHolder = (EditListViewHolder) row.getTag();
        }
        {
            listViewHolder.exerciseName.setText(exerciseNames[position]);
        }
        try {

            listViewHolder.reps.setText(reps[position].toString());
            listViewHolder.weight.setText(weights[position].toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        listViewHolder.addReps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, listViewHolder.reps, 1);
                    (reps[position] = reps[position] + 1).toString();
                }
            }
        });
        listViewHolder.subtractReps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customButtonListener != null) {
                    customButtonListener.onButtonClickListener(position, listViewHolder.reps, -1);
                    if (reps[position] > 0)
                        (reps[position] = reps[position] - 1).toString();
                }
            }
        });
        listViewHolder.weight.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }
            @Override
            public void afterTextChanged(Editable arg0) {
                //weights[position] = arg0;
            }
        });

        return row;
    }
}
