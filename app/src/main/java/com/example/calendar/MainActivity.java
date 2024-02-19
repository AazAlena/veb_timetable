package com.example.calendar;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;

    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        Toast.makeText(this, "Hellow", Toast.LENGTH_SHORT).show();


        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Bingo!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, CalendarOneDay.class);
                startActivity(i);
            }
        };
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
    }



}
