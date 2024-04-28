package com.example.calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button settings, calendar, today, week;
    int year, month, day;

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = findViewById(R.id.btn1);
        calendar = findViewById(R.id.btn2);
        today = findViewById(R.id.btn3);
        week = findViewById(R.id.btn4);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

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

//        Toast.makeText(MainActivity.this, (String) (year + "." + month + "." + day), Toast.LENGTH_SHORT).show();
        calendar.setOnClickListener(listenerToCalendar); // показывает календарь премещает в calendar one day
        today.setOnClickListener(listenerToToday); //перемещает в noday

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
