package com.example.calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class Today extends AppCompatActivity {
    ArrayList<String> goalArr;
    ArrayList<Boolean> goalChecked;
    ArrayList<Goal> myGoals;
    Set<String> listGoalsForToday;
    ListView lv;
    MyTodayAdapter adapter;
    Button btn2_today, btn1_today, btn3_today, btn4_today;
    String str;
    Button ButtonAddGoal_today;
    TextView EditTextForNewGoal_today;
    SharedPreferences ListGoalsSharedPreferences;
    String todayDataForNameInSharedPreferences;
    Integer schGoalsForNameInSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today);

        Intent i = getIntent();//достаём посылку
        str = i.getStringExtra("btn2"); //вынимаем btn1
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

//        работа с shared preferences
        todayDataForNameInSharedPreferences = "28.04.2024";
//условное добавление
        saveGoalsInSharedPreferences("a true", todayDataForNameInSharedPreferences);
        saveGoalsInSharedPreferences("b false", todayDataForNameInSharedPreferences);
        saveGoalsInSharedPreferences("c false", todayDataForNameInSharedPreferences);

        goalArr = new ArrayList<>();
        goalChecked = new ArrayList<>();

        for (String oneGoal : getGoalsFromSharedPreferences(todayDataForNameInSharedPreferences)){
            goalArr.add(oneGoal.split(" ")[0]);
            goalChecked.add(Boolean.valueOf(oneGoal.split(" ")[1]));
        }

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
        myGoals = makeGoal(goalArr, goalChecked);
        adapter = new MyTodayAdapter(this, myGoals);
        lv = findViewById(R.id.lv_checklists);
        lv.setAdapter(adapter);

        ButtonAddGoal_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = EditTextForNewGoal_today.getText().toString();
                Goal newGoal = new Goal();
                newGoal.did = false;
                newGoal.name = str;
                myGoals.add(newGoal);

//                работа с shared preferences
                saveGoalsInSharedPreferences(str+" false", todayDataForNameInSharedPreferences);
                for(String i:getGoalsFromSharedPreferences(todayDataForNameInSharedPreferences)){
                    Toast.makeText(Today.this, i, Toast.LENGTH_SHORT).show();
                }

                adapter.notifyDataSetChanged();
            }
        });

//        !!!!!!!!!!!!!!!!!!
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Today.this, "Удаляю", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ArrayList<Goal> makeGoal(ArrayList <String>goalArr, ArrayList <Boolean>goalChecked) {
        ArrayList<Goal> arr = new ArrayList<>();

        for (int i = 0; i < goalArr.size(); i++) {
            Goal goal = new Goal();
            goal.name = goalArr.get(i);
            goal.did = goalChecked.get(i);

            arr.add(goal);
        }
        return arr;
    }

    void saveGoalsInSharedPreferences(String newGoal, String todayDataForNameInSharedPreferences1){
        ListGoalsSharedPreferences = getSharedPreferences("ListGoalsSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = ListGoalsSharedPreferences.edit();

        Set <String> listGoalsForAdd = new HashSet();
        listGoalsForAdd = ListGoalsSharedPreferences.getStringSet(todayDataForNameInSharedPreferences, new HashSet<String>());
        listGoalsForAdd.add(newGoal);

        editor.remove(todayDataForNameInSharedPreferences1);
        editor.putStringSet(todayDataForNameInSharedPreferences1, listGoalsForAdd);
        editor.commit();

    }
    ArrayList<String> getGoalsFromSharedPreferences(String todayDataForNameInSharedPreferences1){
        ArrayList <String> goalsList = new ArrayList();
        ListGoalsSharedPreferences = getSharedPreferences("ListGoalsSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = ListGoalsSharedPreferences.edit();
        for (String i : ListGoalsSharedPreferences.getStringSet(todayDataForNameInSharedPreferences1, new HashSet<String>())){
            goalsList.add(i);
        }
        return goalsList;
    }

}

