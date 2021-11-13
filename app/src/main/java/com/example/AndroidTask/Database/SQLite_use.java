package com.example.AndroidTask.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLite_use {
    public MyHelper helper;
    public SQLite_use(Context context) {
        helper=new MyHelper(context);
    }

    public void insert(String account, String password){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("account",account);
        values.put("password",password);
        long id =db.insert("information",null,values);
        db.close();
    }

    public int delete(String account) {
        SQLiteDatabase db=helper.getWritableDatabase();
        int number=db.delete("information","account=?",new String[]{account+""});
        db.close();
        return number;
    }
    public int update(String account, String password){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("password",password);
        int number=db.update("information",values,"account=?",new String[]{account+""});
        db.close();
        return number;
    }
    public void find(String account){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor =db.query("information",null,"account=?",new String[]{account+""},null,null,null);
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String Account =cursor.getString(cursor.getColumnIndex("account"));
                @SuppressLint("Range") String password =cursor.getString(cursor.getColumnIndex("password"));
            }
        }
        cursor.close();
        db.close();
    }

}
