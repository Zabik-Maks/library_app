package com.mirea.kt.libraryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyAppSQLiteHelper extends SQLiteOpenHelper {

    public MyAppSQLiteHelper(Context c, String name, SQLiteDatabase.CursorFactory f, int version) {
        super(c, name, f, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + "TABLE_BOOK" + " (" +
                "_id integer primary key autoincrement," +
                "shelf integer," +
                "code text," +
                "author text," +
                "rack integer," +
                "title text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // если версия изменилась то вносим нужные нам изменения
    }

}
