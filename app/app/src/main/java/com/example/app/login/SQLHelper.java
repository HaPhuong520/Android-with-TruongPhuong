package com.example.app.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "Person";
    static final int DB_VERSION = 2;
    static final String DB_TABLE_NAME = "Person";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+DB_TABLE_NAME +
                "(id INTEGER NOT NULL PRIMARY KEY," +
                "username Text,"+
                "password Text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion!=newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NAME);
            onCreate(db);
        }
    }
    public void insertPerson(Person person){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("username",person.getUsername());
        contentValues.put("password",person.getPassword());
        sqLiteDatabase.insert(DB_TABLE_NAME,null,contentValues);
    }
    public void upDatePerson(int id, Person person){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("username",person.getUsername());
        contentValues.put("password",person.getPassword());
        sqLiteDatabase.update(DB_TABLE_NAME,contentValues,"id=?",new String[]{id+""});
    }
    public void deletePerson(int id){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_NAME,"id=?",new String[]{id+""});
    }
}
