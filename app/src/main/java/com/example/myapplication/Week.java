package com.example.myapplication;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Week extends AppCompatActivity {
    ArrayList<String> goalArr;
    ArrayList<String> goalData;
    ArrayList<Boolean> goalChecked;
    ArrayList<GoalWeek> myGoals;
    ListView lv;
    MyWeekAdapter adapter;
    Button settings_week, calendar_week, today_week, week_week;
    String todayDataGetted;
    SharedPreferences ListGoalsSharedPreferences;
    String todayDataForNameInSharedPreferences;
    String startDataForNameInSharedPreferences;
    String endDataForNameInSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week);

        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
        }

        int realYear = year;
        int realMonth = month;
        int realDay = day;

        Intent j = getIntent();//достаём посылку
        todayDataGetted = j.getStringExtra("from calendarOneDay to week "); //вынимаем from calendarOneDay to today

        todayDataForNameInSharedPreferences = realDay + "." + realMonth + "." + realYear;
        startDataForNameInSharedPreferences = Integer.toString(realDay - dayOfWeek) + "." + realMonth + "." + realYear;
        endDataForNameInSharedPreferences = Integer.toString(realDay + (7 - dayOfWeek)) + "." + realMonth + "." + realYear;

        goalArr = new ArrayList<>();
        goalChecked = new ArrayList<>();
        goalData = new ArrayList<>();

        for (int i = realDay - dayOfWeek + 1; i <= realDay + (7 - dayOfWeek) + 1; i++) {
            for (String oneGoal : getGoalsFromSharedPreferences((i + "." + Integer.toString(realMonth+1) + "." + realYear))) {
                goalArr.add(oneGoal.split(" / ")[0]);
                goalChecked.add(Boolean.valueOf(oneGoal.split(" / ")[1]));
                goalData.add((i + "." + Integer.toString(realMonth+1) + "." + realYear));
            }
        }

        settings_week = findViewById(R.id.btn1_week);
        calendar_week = findViewById(R.id.btn2_week);
        today_week = findViewById(R.id.btn3_week);
        week_week = findViewById(R.id.btn4_week);

        week_week.setBackgroundColor(getColor(R.color.moon_dop));

        View.OnClickListener listenerSettings=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "from today to settings";
                Intent i = new Intent(Week.this, MainActivity.class);
                i.putExtra("result", str);
                startActivityForResult(i, 0);
            }
        };

        View.OnClickListener listenerToToday = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = ( realDay + "." + (realMonth+1) + "." + realYear).toString();
                Intent i = new Intent(Week.this, Today.class);
                i.putExtra("from calendarOneDay to today ", str);
                startActivityForResult(i, 0);
            }
        };

        View.OnClickListener listenerToCalendar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Week.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                        String str = (dayOfMonth + "." + (monthOfYear+1) + "." + year1).toString();
                        Intent i = new Intent(Week.this, Today.class);
                        i.putExtra("from calendarOneDay to today ", str);
                        startActivityForResult(i, 0);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        };

        settings_week.setOnClickListener(listenerSettings);
        today_week.setOnClickListener(listenerToToday);
        calendar_week.setOnClickListener(listenerToCalendar);

        myGoals = makeGoalWeek(goalArr, goalChecked, goalData);
        adapter = new MyWeekAdapter(this, myGoals);
        lv = findViewById(R.id.lv_checklists_week);
        lv.setAdapter(adapter);

    }

    ArrayList<GoalWeek> makeGoalWeek(ArrayList <String>goalArr, ArrayList <Boolean>goalChecked, ArrayList <String>goalData) {
        ArrayList<GoalWeek> arr = new ArrayList<>();

        for (int i = 0; i < goalArr.size(); i++) {
            GoalWeek goal = new GoalWeek();
            goal.name = goalArr.get(i);
            goal.did = goalChecked.get(i);
            goal.data = goalData.get(i);

            arr.add(goal);
        }
        return arr;
    }

    void saveGoalsInSharedPreferences(String newGoal, String todayDataForNameInSharedPreferences1){
        ListGoalsSharedPreferences = getSharedPreferences("ListGoalsSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = ListGoalsSharedPreferences.edit();

        Set <String> listGoalsForAdd = new HashSet();
        listGoalsForAdd = ListGoalsSharedPreferences.getStringSet(todayDataForNameInSharedPreferences1, new HashSet<String>());
        listGoalsForAdd.add(newGoal);

        editor.remove(todayDataForNameInSharedPreferences1);
        editor.apply();
        editor.putStringSet(todayDataForNameInSharedPreferences1, listGoalsForAdd);
        editor.apply();

    }
    ArrayList<String> getGoalsFromSharedPreferences(String todayDataForNameInSharedPreferences1){
        ArrayList <String> goalsList = new ArrayList();
        ListGoalsSharedPreferences = getSharedPreferences("ListGoalsSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = ListGoalsSharedPreferences.edit();
        for (String i : ListGoalsSharedPreferences.getStringSet(todayDataForNameInSharedPreferences1, new HashSet<String>())){
            goalsList.add(i);
        }
        return goalsList;
    }

}


