package com.example.a4_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    String num1,num2,op;
    Double sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=findViewById(R.id.edit);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"1");
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"2");
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"3");
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"4");
            }
        });
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"5");
            }
        });
        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"6");
            }
        });
        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"7");
            }
        });
        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"8");
            }
        });
        findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"9");
            }
        });
        findViewById(R.id.btn0).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+"0");
            }
        });
        findViewById(R.id.dot).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText(e1.getText().toString()+".");
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                num1=e1.getText().toString();
                e1.setText("");
                op="+";
            }
        });
        findViewById(R.id.sub).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                num1=e1.getText().toString();
                e1.setText("");
                op="-";
            }
        });
        findViewById(R.id.mul).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                num1=e1.getText().toString();
                e1.setText("");
                op="*";
            }
        });
        findViewById(R.id.div).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                num1=e1.getText().toString();
                e1.setText("");
                op="/";
            }
        });
        findViewById(R.id.eq).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                num2 = e1.getText().toString();
                if(op=="+") {
                    sum=Double.parseDouble(num1) + Double.parseDouble(num2);
                }else if(op=="-") {
                    sum=Double.parseDouble(num1) - Double.parseDouble(num2);
                }else if(op=="*") {
                    sum=Double.parseDouble(num1) * Double.parseDouble(num2);
                }else if(op=="/") {
                    sum=Double.parseDouble(num1) / Double.parseDouble(num2);
                }
                else{
                    sum=0.0;
                }
                e1.setText(Double.toString(sum));
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                e1.setText("");
                num1="0";
                num2="0";
            }
        });
    }
}