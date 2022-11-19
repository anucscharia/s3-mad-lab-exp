package com.example.a11_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_add extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    CDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        e1=(EditText) findViewById(R.id.t1);
        e2=(EditText) findViewById(R.id.t2);
        e3=(EditText) findViewById(R.id.t3);
        e4=(EditText) findViewById(R.id.t4);
        e5=(EditText) findViewById(R.id.t5);
        db=new CDB(this);
    }
    public void onInsert(View v){
        String fname,lname,dob,place,phone;
        fname=e1.getText().toString();
        lname=e2.getText().toString();
        dob=e3.getText().toString();
        place=e4.getText().toString();
        phone=e5.getText().toString();
        Toast.makeText(this,fname+" " +lname, Toast.LENGTH_SHORT).show();
        db.addPerson(fname,lname,dob,place,phone);
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
    }
}