package com.example.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MyWeekAdapter extends ArrayAdapter<GoalWeek> {
    public MyWeekAdapter(Context context,  ArrayList<GoalWeek> arr) {
        super(context, R.layout.item_week, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        !!!!!!!!!!!!!!!!!!!

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
                goal.did = ((CheckBox) v).isChecked();}
        });
        return convertView;
    }
}



