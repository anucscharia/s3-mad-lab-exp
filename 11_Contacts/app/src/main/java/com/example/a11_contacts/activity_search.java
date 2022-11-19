package com.example.a11_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_search extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    CDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        e1=(EditText) findViewById(R.id.t1);
        e2=(EditText) findViewById(R.id.t2);
        e3=(EditText) findViewById(R.id.t3);
        e4=(EditText) findViewById(R.id.t4);
        e5=(EditText) findViewById(R.id.t5);
        db=new CDB(this);

    }
    public void onSearch(View v){
        String a[];
        try{
            String fname=e1.getText().toString();
            a=db.getOnePerson((fname));
            if(a[0]!="")
            {
                e2.setText(a[0]);
                e3.setText(a[1]);
                e4.setText(a[2]);
                e5.setText(a[3]);
            }
            else{
                Toast.makeText(this,"Not Found..", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){

        }

    }
}