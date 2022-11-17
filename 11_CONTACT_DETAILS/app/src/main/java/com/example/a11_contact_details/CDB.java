package com.example.a11_contact_details;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="DMS";
    public CDB(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        arg0.execSQL("create table contact(fname text,lname text,phone string,dob string,place text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(arg0);
    }
    public void add(String fn,String ln,String ph,String dob,String pl){
       try
       {
          SQLiteDatabase db=this.getWritableDatabase();
           ContentValues cv=new ContentValues();
           cv.put("fname",fn);
           cv.put("lname",ln);
           cv.put("phone",ph);
           cv.put("dob",dob);
           cv.put("place",pl);
           db.insert("contact",null,cv);
           db.close();
       }
       catch (Exception e)
       {
           System.out.println(e);
       }
    }
    public String[] getOneDepartment(String fname) {
        String a[] = new String[2];
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("contact", new String[]{"fname","lname","phone","dob","place"}, "fname" + "+?", new String[]{fname}, null, null, null, null);
            if (cursor != null && cursor.getCount() != 0) {
                cursor.moveToFirst();
                a[0] = cursor.getString(1);
                a[1] = cursor.getString(2);
            } else {
                a[0] = "";
                a[1] = "";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
    public int deleteDept(int dno){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("TbDept","dno=?",new String[]{String.valueOf(dno)});
    }
    public void update(String fn,String ln,String ph,String dob,String pl){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("fname",fn);
        values.put("lname",ln);
        values.put("phone",ph);
        values.put("dob",dob);
        values.put("place",pl);
        db.update("contact",values,"fname=?",new String[]{fn});
        db.close();
    }
    }

