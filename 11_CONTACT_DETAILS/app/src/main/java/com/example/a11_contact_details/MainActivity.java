package com.example.a11_contact_details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    CDB db = new CDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.t1);
        e2=(EditText)findViewById(R.id.t2);
        e3=(EditText)findViewById(R.id.t3);
        e4=(EditText)findViewById(R.id.t4);
        e5=(EditText)findViewById(R.id.t5);
    }
    public void onAdd(View view){
        e1.setVisibility(View.VISIBLE);
        e2.setVisibility(View.VISIBLE);
        e3.setVisibility(View.VISIBLE);
        e4.setVisibility(View.VISIBLE);
        e5.setVisibility(View.VISIBLE);
        Button btn=(Button)findViewById(R.id.submit);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String fn, ln, ph, dob, pl;
                fn = e2.getText().toString();
                ln = e2.getText().toString();
                ph = e3.getText().toString();
                dob = e2.getText().toString();
                pl = e3.getText().toString();
                db.add(fn, ln, ph, dob, pl);
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");
                e1.setVisibility(View.INVISIBLE);
                e2.setVisibility(View.INVISIBLE);
                e3.setVisibility(View.INVISIBLE);
                e4.setVisibility(View.INVISIBLE);
                e5.setVisibility(View.INVISIBLE);
                btn.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void onSearch(View view){
        e1.setVisibility(View.VISIBLE);
        Button btn=(Button)findViewById(R.id.submit);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String a[];
                    String fn = e1.getText().toString();
                    a= db.getOneDepartment(fn);
                    if (a[0] != "") {
                        e2.setText(a[0]);
                        e3.setText(a[1]);
                        e4.setText(a[2]);
                        e5.setText(a[3]);
                        e2.setVisibility(View.VISIBLE);
                        e3.setVisibility(View.VISIBLE);
                        e4.setVisibility(View.VISIBLE);
                        e5.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
    public void onDelete(View view){
        String fn=e1.getText().toString();
        db.deleteDept(Integer.parseInt(fn));
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);
        e5.setVisibility(View.INVISIBLE);
    }
    public void onUpdate(View view){
        String fn=e1.getText().toString();
        String ln,ph,dob,pl;
        ln=e2.getText().toString();
        ph=e3.getText().toString();
        dob=e4.getText().toString();
        pl=e5.getText().toString();
        db.update(fn,ln,ph,dob,pl);
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e1.setVisibility(View.INVISIBLE);
        e2.setVisibility(View.INVISIBLE);
        e3.setVisibility(View.INVISIBLE);
        e4.setVisibility(View.INVISIBLE);
        e5.setVisibility(View.INVISIBLE);
    }
}