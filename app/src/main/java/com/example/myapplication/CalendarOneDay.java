package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class CalendarOneDay extends AppCompatActivity {
    Button settings_cal_one_day, calendar_cal_one_day, today_cal_one_day, week_cal_one_day;
    int year, month, day;
    String str;
    TextView textView_cal_one_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_one_day);
        Intent i = getIntent();//достаём посылку
        str = i.getStringExtra("toCalendarOneDay"); //вынимаем btn2
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        settings_cal_one_day = findViewById(R.id.btn1_cal_one_day);
        calendar_cal_one_day = findViewById(R.id.btn2_cal_one_day);
        today_cal_one_day = findViewById(R.id.btn3_cal_one_day);
        week_cal_one_day = findViewById(R.id.btn4_cal_one_day);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        int realYear = year;
        int realMonth = month;
        int realDay = day;

        View.OnClickListener listenerToToday = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(CalendarOneDay.this, "Bingo!", Toast.LENGTH_SHORT).show();
                String str = (realDay+ "." + realMonth + "." + realYear).toString();
                Intent i = new Intent(CalendarOneDay.this, Today.class);
                i.putExtra("from calendarOneDay to today ", str);
                startActivityForResult(i, 0);
            }
        };
        View.OnClickListener listenerToCalendar = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarOneDay.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(CalendarOneDay.this, (String) (dayOfMonth + " " + monthOfYear + " " + year1), Toast.LENGTH_SHORT).show();
                        String str = (dayOfMonth + "." + monthOfYear + "." + year1).toString();
                        Intent i = new Intent(CalendarOneDay.this, Today.class);
//                        i.putExtra("toCalendarOneDay", str);
                        i.putExtra("from calendarOneDay to today", str);
                        startActivityForResult(i, 0);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        };
        View.OnClickListener listenerToSettings = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "from Calrndar one day to settings";
                Intent i = new Intent(CalendarOneDay.this, MainActivity.class);
                i.putExtra("btn1", str);
                startActivityForResult(i, 0);
            }
        };
        Toast.makeText(CalendarOneDay.this, (String) (day + " " + month + " " + year), Toast.LENGTH_SHORT).show();

        settings_cal_one_day.setOnClickListener(listenerToSettings);
        today_cal_one_day.setOnClickListener(listenerToToday); // перемещает между первой и today активностью
        calendar_cal_one_day.setOnClickListener(listenerToCalendar); //показывает календарь и перемещает в активность calrndar_one_day

// а теперь разборки с сождержимым


    }
}