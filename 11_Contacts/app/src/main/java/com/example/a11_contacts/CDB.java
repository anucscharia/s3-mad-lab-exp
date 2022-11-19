package com.example.a11_contacts;
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
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        arg0.execSQL("create table TbDept(fname text, lname text,dob text,place text,phone text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        arg0.execSQL("DROP TABLE IF EXISTS TbDept");
        onCreate(arg0);

    }
    public void addPerson(String fn,String ln,String dob,String pl,String ph){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put("fname",fn);

            cv.put("lname",ln);
            cv.put("dob",dob);
            cv.put("place",pl);
            cv.put("phone",ph);
            db.insert("TbDept",null,cv);
            db.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public String[] getOnePerson(String fname)
    {
        String a[]=new String[4];
        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.query("TbDept",new String[]{"fname","lname","dob","place","phone"},"fname"+"=?",new String[]{fname},null,null,null,null);
            if (cursor!=null && cursor.getCount()!=0){
                cursor.moveToFirst();
                a[0]=cursor.getString(1);
                a[1]=cursor.getString(2);
                a[2]=cursor.getString(3);
                a[3]=cursor.getString(4);
                a[4]=cursor.getString(5);
            }
            else{
                a[0]="";
                a[1]="";
                a[2]="";
                a[3]="";
                a[4]="";
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return a;

    }
    public int deletePerson(String fname){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("TbDept","fname=?",new String[] {String.valueOf(fname)});
    }
    public void update(String fname,String lname,String dob,String place,String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("lname",lname);
        values.put("dob",dob);
        values.put("place",place);
        values.put("phone",phone);
        db.update("TbDept",values,"fname=?",new String[]{String.valueOf(fname)});
        db.close();
    }
    public String getName() {
        String TABLE_NAME="TbDept";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT name FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}