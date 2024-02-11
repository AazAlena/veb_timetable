package com.example.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyCalendar extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.calendar, container, false);
        CalendarView calendarView = v.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String text = year + " " + month + " " + dayOfMonth;
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
            }
        });

//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.calendar);
//
//        CalendarView calendarView = findViewById(R.id.calendarView);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year,
//                                            int month, int dayOfMonth) {
//                int mYear = year;
//                int mMonth = month;
//                int mDay = dayOfMonth;
//                String selectedDate = new StringBuilder().append(mMonth + 1)
//                        .append("-").append(mDay).append("-").append(mYear)
//                        .append(" ").toString();
//                Toast.makeText(getApplicationContext(), selectedDate, Toast.LENGTH_LONG).show();
//
//            }
//        });

        return v;
    }
}
