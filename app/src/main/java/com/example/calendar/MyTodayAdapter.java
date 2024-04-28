package com.example.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;


public class MyTodayAdapter extends ArrayAdapter<Goal> {
    public MyTodayAdapter(Context context,  ArrayList<Goal> arr) {
        super(context, R.layout.item, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        !!!!!!!!!!!!!!!!!!!

        final Goal goal = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, null);
        }

        CheckBox ch = (CheckBox) convertView.findViewById(R.id.checkBox);
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