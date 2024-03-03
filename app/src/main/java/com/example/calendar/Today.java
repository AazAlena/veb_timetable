package com.example.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Today extends AppCompatActivity {
    Button btn2_today, btn1_today, btn3_today, btn4_today;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today);

        Intent i = getIntent();//достаём посылку
        str = i.getStringExtra("btn2"); //вынимаем btn1
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        btn1_today = findViewById(R.id.btn1_today);
        btn2_today = findViewById(R.id.btn2_today);
        btn3_today = findViewById(R.id.btn3_today);
        btn4_today = findViewById(R.id.btn4_today);

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




    }
}

