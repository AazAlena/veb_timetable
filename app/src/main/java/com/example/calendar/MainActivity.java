package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment f;
//                if (view.getId() == R.id.btn1) {
                    f = new MyCalendar();
//                } else if (view.getId()==R.id.btn2) {
//                    f = new MyF1();
//                } else {
//                    f = new MyF2();
//                }
//                SampleFragmentB fb = new SampleFragmentB();
                ft.add(R.id.frag, f);
                ft.commit();

                Toast.makeText(MainActivity.this, "CLick" + view.getId(), Toast.LENGTH_SHORT).show();
            }
        };
        btn1.setOnClickListener(listener);
//        btn2.setOnClickListener(listener);
//        btn3.setOnClickListener(listener);
    }
}
