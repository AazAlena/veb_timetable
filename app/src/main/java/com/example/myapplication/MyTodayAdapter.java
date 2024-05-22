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
import android.widget.Toast;

import com.example.myapplication.Goal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MyTodayAdapter extends ArrayAdapter<Goal> {
    ArrayList<Goal> arr;
    public MyTodayAdapter(Context context,  ArrayList<Goal> arr) {
        super(context, R.layout.item, arr);
        this.arr=arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

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
                goal.did = ((CheckBox) v).isChecked();
            }
        });

        ImageView bin_delet = convertView.findViewById(R.id.bin_delet);
        bin_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arr.remove(position);
                notifyDataSetChanged();
            }
        } );
        return convertView;
    }



}