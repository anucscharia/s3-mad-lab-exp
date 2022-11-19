package com.example.a11_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class activity_delete extends AppCompatActivity {
    EditText e1;
    CDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        e1=(EditText) findViewById(R.id.t1);

        db=new CDB(this);
    }
    public void onDelete(View v){
        String fname=e1.getText().toString();
        db.deletePerson(fname);
        e1.setText("");
    }
}