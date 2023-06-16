package com.mirea.kt.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class BookActivity extends AppCompatActivity {

    private  DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));
        RecyclerView rcView = findViewById(R.id.recyclerView);
        BookAdapter adapter = new BookAdapter(dbManager.loadAllBooksFromDatabase(), this);
        Log.i("database", "aded");
        rcView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcView.setAdapter(adapter);
    }
}