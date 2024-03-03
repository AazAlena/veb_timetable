package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalendarOneDay extends AppCompatActivity {
    Button btn1_cal_one_day, btn2_cal_one_day, btn3_cal_one_day, btn4_cal_one_day;
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

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent();
                i2.putExtra("result", "hello " + str);
                setResult(0, i2);
                finish();
            }
        };

        btn1_cal_one_day.setOnClickListener(listener);
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