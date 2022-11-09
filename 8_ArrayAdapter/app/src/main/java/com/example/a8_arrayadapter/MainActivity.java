package com.example.a8_arrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView List1,List2;
    String code[]={"Code","01","02","03","04","05","06"};
    String country[] = {"Country","India","Bhutan","Sre Lanka","Japan","USA","UAE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List1 = (ListView) findViewById(R.id.listview1);
        List2= (ListView) findViewById(R.id.listview2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView1, country);
        List1.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView2, code);
        List2.setAdapter(arrayAdapter2);


    }

}
