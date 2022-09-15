package com.example.a3_layout;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    RadioButton b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(RadioButton) findViewById(R.id.radioButton1);
        b2=(RadioButton) findViewById(R.id.radioButton2);
        b3=(RadioButton) findViewById(R.id.radioButton3);
        b4=(RadioButton)findViewById(R.id.radioButton4);
        b5=(RadioButton)findViewById(R.id.radioButton5);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                    if (checked) {
                        Intent i = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(i);
                    }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                if (checked) {
                    Intent i = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(i);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                    if (checked){
                        Intent i = new Intent(MainActivity.this, MainActivity4.class);
                        startActivity(i);
                    }
                }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                if (checked){
                    Intent i = new Intent(MainActivity.this, MainActivity5.class);
                    startActivity(i);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean checked = ((RadioButton) v).isChecked();
                if (checked){
                    Intent i = new Intent(MainActivity.this, MainActivity6.class);
                    startActivity(i);
                }
            }
        });
    }
}