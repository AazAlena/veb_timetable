package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;
    int GlobalYear;
    int GlobalMonth;
    int GlobalDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment f;
                f = new MyCalendarOneDay();
                if (view.getId() == R.id.btn1) {
                    f = new MyCalendarOneDay();
                } else if (view.getId() == R.id.btn2) {
                    final Calendar c = Calendar.getInstance();

                    // on below line we are getting
                    // our day, month and year.
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);

                    // on below line we are creating a variable for date picker dialog.
                    DatePickerDialog datePickerDialog = new DatePickerDialog(
                            // on below line we are passing context.
                            MainActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    // on below line we are setting date to our text view.
                                    Toast.makeText(MainActivity.this, (String)(year + " "+ monthOfYear), Toast.LENGTH_SHORT).show();
                                    GlobalYear = year;
                                    GlobalMonth = monthOfYear;
                                    GlobalDate = dayOfMonth;
                                }
                            },
                            // on below line we are passing year,
                            // month and day for selected date in our date picker.
                            year, month, day);
                    // at last we are calling show to
                    // display our date picker dialog.
                    datePickerDialog.show();

                } else if (view.getId() == R.id.btn3){
                    f = new MyToday();
                }
                ft.add(R.id.frag, f);
                ft.commit();

                Toast.makeText(MainActivity.this, "CLick" + view.getId(), Toast.LENGTH_SHORT).show();
            }
        };
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
    }
}
