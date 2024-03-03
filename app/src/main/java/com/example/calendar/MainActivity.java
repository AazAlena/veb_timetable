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
    Button btn1, btn2, btn3, btn4;
    int year, month, day;

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Bingo!", Toast.LENGTH_SHORT).show();
                String str = (year + " " + month + " " + day).toString();
                Intent i = new Intent(MainActivity.this, Today.class);
                i.putExtra("btn2", str);
                startActivityForResult(i, 0);
            }
        };
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(MainActivity.this, (String) (year + " " + monthOfYear + " " + dayOfMonth), Toast.LENGTH_SHORT).show();
                        String str = (year + " " + monthOfYear + " " + dayOfMonth).toString();
                        Intent i = new Intent(MainActivity.this, CalendarOneDay.class);
                        i.putExtra("toCalendarOneDay", str);
                        startActivityForResult(i, 0);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        };

        Toast.makeText(MainActivity.this, (String) (year + " " + month + " " + day), Toast.LENGTH_SHORT).show();
        btn2.setOnClickListener(listener); // перемещает между первой и today активностью
        btn3.setOnClickListener(listener2); //показывает календарь и перемещает в активность calrndar_one_day

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
//
//                if (view.getId() == R.id.btn1) {
//                        f = new MyCalendarOneDay();
//                        }
//                        else if (view.getId() == R.id.btn2) {
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(
//
//                        MainActivity.this,
//                        new DatePickerDialog.OnDateSetListener() {
//
//@Override
//public void onDateSet(DatePicker view, int year,
//        int monthOfYear, int dayOfMonth) {
//
//        Toast.makeText(MainActivity.this, (String)(year + " "+ monthOfYear+" "+dayOfMonth), Toast.LENGTH_SHORT).show();
//        GlobalYear = year;
//        GlobalMonth = monthOfYear;
//        GlobalDate = dayOfMonth;
//        }
//        },
//        year, month, day);
//
//        datePickerDialog.show();
//        f = new MyToday();
