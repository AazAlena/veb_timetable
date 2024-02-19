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
                String str = "mmmm".toString();
                Intent i = new Intent(MainActivity.this, CalendarOneDay.class);
                i.putExtra("btn2", str);
                startActivityForResult(i, 0);
            }
        };
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 & requestCode == 0) {
            String str = data.getStringExtra("result");
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        }
    }
}
