package com.example.a2_activity_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"onCreate() Called",Toast.LENGTH_LONG).show();
        Log.e(TAG,"onCreate() called");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart() Called",Toast.LENGTH_LONG).show();
        Log.e(TAG,"onStart() called");
    }
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),"onRestart() Called",Toast.LENGTH_LONG).show();
        Log.e(TAG,"onRestart() called");
    }
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause() Called",Toast.LENGTH_LONG).show();
        Log.e(TAG,"onPause() called");
    }
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy() Called",Toast.LENGTH_LONG).show();
        Log.e(TAG,"onDestroy() called");    }
}