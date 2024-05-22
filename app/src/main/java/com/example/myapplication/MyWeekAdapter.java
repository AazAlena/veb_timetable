package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyWeekAdapter extends ArrayAdapter<GoalWeek> {
    ArrayList<GoalWeek> arr;
    SharedPreferences ListGoalsSharedPreferences;
    public MyWeekAdapter(Context context,  ArrayList<GoalWeek> arr) {
        super(context, R.layout.item_week, arr);
        this.arr=arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final GoalWeek goal = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_week, null);
        }

        TextView dataGoal = convertView.findViewById(R.id.data_goal);
        CheckBox ch = (CheckBox) convertView.findViewById(R.id.checkBox);

        dataGoal.setText(goal.data);
        ch.setChecked(goal.did);
        ch.setText(goal.name);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal.did = ((CheckBox) v).isChecked();

                ListGoalsSharedPreferences = getContext().getSharedPreferences("ListGoalsSharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = ListGoalsSharedPreferences.edit();

                Set <String> listGoalsForAdd = new HashSet();
                listGoalsForAdd = ListGoalsSharedPreferences.getStringSet(goal.data, new HashSet<String>());
                listGoalsForAdd.remove(goal.name + " / " + !goal.did);
                listGoalsForAdd.add(goal.name + " / " + goal.did);

                editor.remove(goal.data);
                editor.apply();
                editor.putStringSet(goal.data, listGoalsForAdd);
                editor.apply();
            }
        });

        ImageView bin_delet = convertView.findViewById(R.id.bin_delet);
        bin_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListGoalsSharedPreferences = getContext().getSharedPreferences("ListGoalsSharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = ListGoalsSharedPreferences.edit();

                Set <String> listGoalsForAdd = new HashSet();
                listGoalsForAdd = ListGoalsSharedPreferences.getStringSet(goal.data, new HashSet<String>());
                listGoalsForAdd.remove(goal.name + " / " + goal.did);

                editor.remove(arr.get(position).data);
                editor.apply();
                editor.putStringSet(goal.data, listGoalsForAdd);
                editor.apply();

                arr.remove(position);
                notifyDataSetChanged();

            }
        } );
        return convertView;
    }
}



