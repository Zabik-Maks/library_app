package com.mirea.kt.libraryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;



public class DBManager {
    private SQLiteOpenHelper sqLiteHelper;

    public DBManager(SQLiteOpenHelper sqLiteHelper) {
        this.sqLiteHelper = sqLiteHelper;
    }

    public boolean saveBookToDatabase(Book book) {
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase(); // получили ссылку на базу данных
        // создание переменной для хранения данных в формате "ключ-значение"
        // по сути формирyем запись для вставки в БД
        ContentValues cv = new ContentValues();
        cv.put("shelf", book.getShelf());
        cv.put("code", book.getCode());
        cv.put("author", book.getAuthor());
        cv.put("rack", book.getRack());
        cv.put("title", book.getTitle());
        // вставка записи в таблицу базы данных
        //метод возвращает номер строки в случае успешной вставки или -1, если произошла ошибка
        long rowId = db.insert("TABLE_BOOK", null, cv);
        cv.clear(); // очистка
        db.close(); // закрытие базы данных
        Log.i("book", "saved");
        return rowId != 1; // возвращаем результат вставки
    }


    public ArrayList<Book> loadAllBooksFromDatabase(){
        ArrayList<Book> book = new ArrayList<>();
        SQLiteDatabase db = this.sqLiteHelper.getWritableDatabase();
        Cursor dbCursor = db.query("TABLE_BOOK", null, null, null, null, null,  null);
        if(dbCursor.moveToFirst()){
            do{
                int shelf = dbCursor.getInt(dbCursor.getColumnIndexOrThrow( "shelf"));
                String code = dbCursor.getString(dbCursor.getColumnIndexOrThrow("code"));
                String author = dbCursor.getString(dbCursor.getColumnIndexOrThrow("author"));
                int rack = dbCursor.getInt(dbCursor.getColumnIndexOrThrow( "rack"));
                String title = dbCursor.getString(dbCursor.getColumnIndexOrThrow("title"));
                book.add(new Book(shelf, code, author, rack, title));
            }while (dbCursor.moveToNext());
        }
        dbCursor.close();
        db.close ();
        return book;
    }

}
