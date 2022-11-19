package com.example.a11_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class activity_update extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    CDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        e1=(EditText) findViewById(R.id.t1);
        e2=(EditText) findViewById(R.id.t2);
        e3=(EditText) findViewById(R.id.t3);
        e4=(EditText) findViewById(R.id.t4);
        e5=(EditText) findViewById(R.id.t5);
        db=new CDB(this);

    }
    public void onUpdate(View v){
        String fname=e1.getText().toString();
        String lname,dob,place,phone;
        lname=e2.getText().toString();
        dob=e3.getText().toString();
        place=e4.getText().toString();
        phone=e5.getText().toString();
        db.update(fname,lname,dob,place,phone);
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
    }
}