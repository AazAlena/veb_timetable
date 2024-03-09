package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Today extends AppCompatActivity {
    ArrayList<String> goalArr;
    ArrayList<Boolean> goalChecked;
    MyTodayAdapter adapter;
    Button btn2_today, btn1_today, btn3_today, btn4_today;
    String str;
    Button ButtonAddGoal_today;
    TextView EditTextForNewGoal_today;
//    public String[] goalArr = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today);

        Intent i = getIntent();//достаём посылку
        str = i.getStringExtra("btn2"); //вынимаем btn1
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        goalArr = new ArrayList<>();
        goalChecked = new ArrayList<>();
        goalArr.add("1");
        goalArr.add("2");
        goalArr.add("3");
        goalArr.add("4");
        goalArr.add("5");
        goalArr.add("6");

        goalChecked.add(true);
        goalChecked.add(true);
        goalChecked.add(false);
        goalChecked.add(false);
        goalChecked.add(true);
        goalChecked.add(false);

        btn1_today = findViewById(R.id.btn1_today);
        btn2_today = findViewById(R.id.btn2_today);
        btn3_today = findViewById(R.id.btn3_today);
        btn4_today = findViewById(R.id.btn4_today);
        ButtonAddGoal_today = findViewById(R.id.ButtonAddGoal_today);
        EditTextForNewGoal_today = findViewById(R.id.EditTextForNewGoal_today);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent();
                i2.putExtra("result", "hello from today" + str);
                setResult(0, i2);
                finish();
            }
        };

        btn1_today.setOnClickListener(listener);


        //АДАПТЕР_item_1,
        adapter = new MyTodayAdapter(this, makeGoal(goalArr, goalChecked));
        ListView lv = (ListView) findViewById(R.id.lv_checklists);
        lv.setAdapter(adapter);

        ButtonAddGoal_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = EditTextForNewGoal_today.getText().toString();
//                Toast.makeText(Today.this, str, Toast.LENGTH_SHORT).show();
//                Goal newGoal = new Goal();
//                newGoal.did = false;
//                newGoal.name = str;
                goalArr.add(str);
                goalChecked.add(false);

//                adapter.add(makeGoal(goalArr, goalChecked)[makeGoal(goalArr, goalChecked).length - 1]);
//                adapter.notifyDataSetChanged();
                adapter = new MyTodayAdapter(Today.this, makeGoal(goalArr, goalChecked));
                lv.setAdapter(adapter);

//                Toast.makeText(Today.this, goalArr.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    Goal[] makeGoal(ArrayList <String>goalArr, ArrayList <Boolean>goalChecked) {
        Goal[] arr = new Goal[goalArr.size()];

        for (int i = 0; i < arr.length; i++) {
            Goal goal = new Goal();
            goal.name = goalArr.get(i);
            goal.did = goalChecked.get(i);

            arr[i] = goal;
        }
        return arr;
    }

}

