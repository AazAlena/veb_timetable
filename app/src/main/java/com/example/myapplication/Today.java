package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Today extends AppCompatActivity {
    ArrayList<String> goalArr;
    ArrayList<Boolean> goalChecked;
    ArrayList<Goal> myGoals;
    ListView lv;
    MyTodayAdapter adapter;
    Button settings_today, calendar_today, today_today, week_today;
    String todayDataGetted;
    Button ButtonAddGoal_today;
    EditText EditTextForNewGoal_today;
    TextView Data_today;
    SharedPreferences ListGoalsSharedPreferences;
    String todayDataForNameInSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int realYear = year;
        int realMonth = month;
        int realDay = day;

        settings_today = findViewById(R.id.btn1_today);
        calendar_today = findViewById(R.id.btn2_today);
        today_today = findViewById(R.id.btn3_today);
        week_today = findViewById(R.id.btn4_today);
        ButtonAddGoal_today = findViewById(R.id.ButtonAddGoal_today);
        EditTextForNewGoal_today = findViewById(R.id.EditTextForNewGoal_today);
        Data_today = findViewById(R.id.data_today);

        Intent i = getIntent();//достаём посылку
        todayDataGetted = i.getStringExtra("from calendarOneDay to today "); //вынимаем from calendarOneDay to today
        todayDataForNameInSharedPreferences = todayDataGetted.toString();

        Data_today.setText(todayDataForNameInSharedPreferences);
        if (todayDataForNameInSharedPreferences.equals(realDay+"."+(realMonth+1)+"."+realYear)){
            today_today.setBackgroundColor(getColor(R.color.moon_dop));
        } else {
            calendar_today.setBackgroundColor(getColor(R.color.moon_dop));
        }

        goalArr = new ArrayList<>();
        goalChecked = new ArrayList<>();

        for (String oneGoal : getGoalsFromSharedPreferences(todayDataForNameInSharedPreferences)){
            goalArr.add(oneGoal.split(" / ")[0]);
            goalChecked.add(Boolean.valueOf(oneGoal.split(" / ")[1]));
        }

        View.OnClickListener listenerSettings=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "from today to settings";
                Intent i = new Intent(Today.this, MainActivity.class);
                i.putExtra("result", str);
                startActivityForResult(i, 0);
            }
        };

        View.OnClickListener listenerToToday = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = ( realDay + "." + (realMonth+1) + "." + realYear).toString();
                Intent i = new Intent(Today.this, Today.class);
                i.putExtra("from calendarOneDay to today ", str);
                startActivityForResult(i, 0);
            }
        };

        View.OnClickListener listenerToCalendar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Today.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                        String str = (dayOfMonth + "." + (monthOfYear+1) + "." + year1).toString();
                        Intent i = new Intent(Today.this, Today.class);
                        i.putExtra("from calendarOneDay to today ", str);
                        startActivityForResult(i, 0);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        };
        View.OnClickListener listenerToWeek = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = ( realDay + "." + (realMonth+1) + "." + realYear).toString();
                Intent i = new Intent(Today.this, Week.class);
                i.putExtra("from calendarOneDay to week ", str);
                startActivityForResult(i, 0);
            }
        };

        week_today.setOnClickListener(listenerToWeek);
        settings_today.setOnClickListener(listenerSettings);
        today_today.setOnClickListener(listenerToToday);
        calendar_today.setOnClickListener(listenerToCalendar);

        myGoals = makeGoal(goalArr, goalChecked);
        adapter = new MyTodayAdapter(this, myGoals);
        lv = findViewById(R.id.lv_checklists);
        lv.setAdapter(adapter);

        ButtonAddGoal_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = EditTextForNewGoal_today.getText().toString();
                Goal newGoal = new Goal();
                newGoal.did = false;
                newGoal.name = str;
                myGoals.add(newGoal);

                saveGoalsInSharedPreferences(str+" / false", todayDataForNameInSharedPreferences);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        ArrayList<Goal> ListOfChangedExistedGoals = new ArrayList();
        ListOfChangedExistedGoals = adapter.arr;
        changeGoalsInSharedPreferences(ListOfChangedExistedGoals, todayDataForNameInSharedPreferences);
    }

    ArrayList<Goal> makeGoal(ArrayList <String>goalArr, ArrayList <Boolean>goalChecked) {
        ArrayList<Goal> arr = new ArrayList<>();

        for (int i = 0; i < goalArr.size(); i++) {
            Goal goal = new Goal();
            goal.name = goalArr.get(i);
            goal.did = goalChecked.get(i);

            arr.add(goal);
        }
        return arr;
    }

    public void changeGoalsInSharedPreferences(ArrayList<Goal> ListOfChangedExistedGoals, String todayDataForNameInSharedPreferences1){
        ListGoalsSharedPreferences = getSharedPreferences("ListGoalsSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = ListGoalsSharedPreferences.edit();
        editor.remove(todayDataForNameInSharedPreferences1);
        editor.apply();

        for (int i = 0; i < ListOfChangedExistedGoals.size(); i++){
            saveGoalsInSharedPreferences(ListOfChangedExistedGoals.get(i).name + " / " + ListOfChangedExistedGoals.get(i).did, todayDataForNameInSharedPreferences1);
            editor.apply();
        }
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

