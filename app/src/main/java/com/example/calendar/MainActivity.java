package com.example.calendar;
//android:background="@drawable/rounded_corner"
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Switch SwitchSun, SwitchMoon;
    Button settings, calendar, today, week;
    int year, month, day;
    LinearLayout LayoutMorning, LayoutEvening;

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = findViewById(R.id.btn1);
        calendar = findViewById(R.id.btn2);
        today = findViewById(R.id.btn3);
        week = findViewById(R.id.btn4);
        LayoutMorning = findViewById(R.id.linearLayoutMorning);
        LayoutEvening = findViewById(R.id.linearLayoutEvening);
        SwitchSun = findViewById(R.id.switchSun);
        SwitchMoon = findViewById(R.id.switchMoon);

        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        int realYear = year;
        int realMonth = month;
        int realDay = day;


        View.OnClickListener listenerToSun = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwitchSun.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SwitchMoon.setChecked(!SwitchMoon.isChecked());
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SwitchMoon.setChecked(!SwitchMoon.isChecked());
                }
            }
        };
        View.OnClickListener listenerToMoon = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwitchMoon.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SwitchSun.setChecked(!SwitchMoon.isChecked());
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SwitchSun.setChecked(!SwitchMoon.isChecked());
                }
            }
        };

        SwitchSun.setOnClickListener(listenerToSun);
        SwitchMoon.setOnClickListener(listenerToMoon);

        View.OnClickListener listenerToToday = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Bingo!", Toast.LENGTH_SHORT).show();
                String str = ( realDay + "." + realMonth + "." + realYear).toString();
                Intent i = new Intent(MainActivity.this, Today.class);
                i.putExtra("from calendarOneDay to today ", str);
                startActivityForResult(i, 0);
            }
        };
        View.OnClickListener listenerToCalendar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(MainActivity.this, (String) (dayOfMonth + " " + monthOfYear + " " + year1), Toast.LENGTH_SHORT).show();
                        String str = (dayOfMonth + "." + monthOfYear + "." + year1).toString();
                        Intent i = new Intent(MainActivity.this, Today.class);
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
//                Toast.makeText(MainActivity.this, "Bingo!", Toast.LENGTH_SHORT).show();
                String str = ( realDay + "." + realMonth + "." + realYear).toString();
                Intent i = new Intent(MainActivity.this, Week.class);
                i.putExtra("from calendarOneDay to week ", str);
                startActivityForResult(i, 0);
            }
        };
        week.setOnClickListener(listenerToWeek);
        calendar.setOnClickListener(listenerToCalendar); // показывает календарь премещает в calendar one day
        today.setOnClickListener(listenerToToday); //перемещает в today



    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 & requestCode == 0) {
            String str = data.getStringExtra("result");
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }
}
