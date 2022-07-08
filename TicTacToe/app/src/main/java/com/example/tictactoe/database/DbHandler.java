package com.example.tictactoe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context,"game.db",null,1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT, email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }


    public Boolean registerUser(String username, String email, String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("email",email);
        values.put("password",password);

        long result = db.insert("users",null, values);
        db.close();

        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean isUserAlreadyExists(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ?",new String[]{email});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean isPassAndEmailCorrect(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?",new String[]{email,password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }


}
