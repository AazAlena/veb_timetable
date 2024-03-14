package com.example.calendar;

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
    Button btn1_cal_one_day, btn2_cal_one_day, btn3_cal_one_day, btn4_cal_one_day;
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

        btn1_cal_one_day = findViewById(R.id.btn1_cal_one_day);
        btn2_cal_one_day = findViewById(R.id.btn2_cal_one_day);
        btn3_cal_one_day = findViewById(R.id.btn3_cal_one_day);
        btn4_cal_one_day = findViewById(R.id.btn4_cal_one_day);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarOneDay.this, "Bingo!", Toast.LENGTH_SHORT).show();
                String str = (year + " " + month + " " + day).toString();
                Intent i = new Intent(CalendarOneDay.this, Today.class);
                i.putExtra("btn2", str);
                startActivityForResult(i, 0);
            }
        };
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CalendarOneDay.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(CalendarOneDay.this, (String) (year + " " + monthOfYear + " " + dayOfMonth), Toast.LENGTH_SHORT).show();
                        String str = (year + " " + monthOfYear + " " + dayOfMonth).toString();
                        Intent i = new Intent(CalendarOneDay.this, CalendarOneDay.class);
                        i.putExtra("toCalendarOneDay", str);
                        startActivityForResult(i, 0);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        };
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i2 = new Intent();
//                i2.putExtra("result", "hello " + str);
//                setResult(0, i2);
//                finish();
                String str = "return from Calrndar one day";
                Intent i = new Intent(CalendarOneDay.this, MainActivity.class);
                i.putExtra("btn1", str);
                startActivityForResult(i, 0);
            }
        };
        Toast.makeText(CalendarOneDay.this, (String) (year + " " + month + " " + day), Toast.LENGTH_SHORT).show();

        btn1_cal_one_day.setOnClickListener(listener1);
        btn2_cal_one_day.setOnClickListener(listener2); // перемещает между первой и today активностью
        btn3_cal_one_day.setOnClickListener(listener3); //показывает календарь и перемещает в активность calrndar_one_day


//        Intent i = getIntent();//достаём посылку
//        str = i.getStringExtra("username"); //вынимаем username
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
//        textView_cal_one_day = findViewById(R.id.textView_cal_one_day);
//        textView_cal_one_day.setText(str);
//
//        btn1_cal_one_day = findViewById(R.id.btn1_cal_one_day);
//        btn2_cal_one_day = findViewById(R.id.btn2_cal_one_day);
//        btn3_cal_one_day = findViewById(R.id.btn3_cal_one_day);
//        btn4_cal_one_day = findViewById(R.id.btn4_cal_one_day);
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (view.getId()==R.id.btn1) {
//                    Intent x = new Intent();
//                    x.putExtra("result", "hello " + str);
//                    setResult(0, x);
//                    finish();
//                } else if (view.getId()==R.id.btn2) {
//                    Toast.makeText(CalendarOneDay.this, "btn2", Toast.LENGTH_SHORT).show();
//                } else if (view.getId()==R.id.btn3) {
//                    Intent i = new Intent(btn3, Today.class);
//                    i.putExtra("username", "посылка для третьей кнопки и активности today"); //посылка
//                    startActivityForResult(i, 0);
//                } else if (view.getId()==R.id.btn4){
//                    Toast.makeText(CalendarOneDay.this, "btn4", Toast.LENGTH_SHORT).show();
//                }
//
//                Toast.makeText(CalendarOneDay.this, "CLick"+view.getId(), Toast.LENGTH_SHORT).show();
//            }
//
//        };
//
//        btn1.setOnClickListener(listener);
//        btn2.setOnClickListener(listener);
//        btn3.setOnClickListener(listener);
//        btn4.setOnClickListener(listener);
//    }
    }
}