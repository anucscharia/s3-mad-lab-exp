package com.example.deptapp;

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
        arg0.execSQL("create table TbDept(dno integer primary key autoincrement,dname text,dloc text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS TbDept");
        onCreate(arg0);
    }
    public void addDept(String dn,String dl){
       try
       {
          SQLiteDatabase db=this.getWritableDatabase();
           ContentValues cv=new ContentValues();
           cv.put("dname",dn);
           cv.put("dloc",dl);
           db.insert("TbDept",null,cv);
           db.close();
       }
       catch (Exception e)
       {
           System.out.println(e);
       }
    }
    public String[] getOneDepartment(int dno) {
        String a[] = new String[2];
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("TbDept", new String[]{"dno", "dname", "dloc"}, "dno" + "+?", new String[]{String.valueOf(dno)}, null, null, null, null);
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
    public void update(int dno,String dn,String dl){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("dname",dn);
        values.put("dloc",dl);
        db.update("TbDept",values,"dno=?",new String[]{String.valueOf(dno)});
        db.close();
    }
    }

