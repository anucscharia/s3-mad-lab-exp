package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2;
    EditText e1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        e1=findViewById(R.id.e1);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(e1.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Empty box",Toast.LENGTH_LONG).show();
                    e1.setError("Enter Result");
                }else {
                    if((Integer.parseInt(t1.getText().toString())+Integer.parseInt(t2.getText().toString()))==Integer.parseInt(e1.getText().toString())){
                        Toast.makeText(getApplicationContext(),"You WIN",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"You LOSE",Toast.LENGTH_LONG).show();
                    }
                }
                String n  = e1.getText().toString();
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(e1,n);
                editor.commit();

            }
        });

    }

}