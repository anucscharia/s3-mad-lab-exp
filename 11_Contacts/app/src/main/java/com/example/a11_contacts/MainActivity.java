package com.example.a11_contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView List1,List2;
    String name[]={"hi"};
    String phone[] = {"hello"};
    CDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onSelect();
    }
    public void onSelect(){
        List1 = (ListView) findViewById(R.id.listview1);
        List2= (ListView) findViewById(R.id.listview2);
        Cursor cu=db.getName();
        name[0]=cu.getString(1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView1,name);
        List1.setAdapter(arrayAdapter);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.activity_list_view, R.id.textView2,phone);
        List2.setAdapter(arrayAdapter2);

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menulist, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.addvalues:
                Intent i1 = new Intent(MainActivity.this,activity_add.class);
                startActivity(i1);
                return true;

            case R.id.deletevalues:
                Intent i2 = new Intent(MainActivity.this, activity_delete.class);
                startActivity(i2);
                return (true);

            case R.id.updatevalues:
                Intent i3 = new Intent(MainActivity.this, activity_update.class);
                startActivity(i3);
                return (true);

            case R.id.searchvalues:
                Intent i4 = new Intent(MainActivity.this, activity_search.class);
                startActivity(i4);
                return (true);

        }
        return (super.onOptionsItemSelected(item));
    }

}