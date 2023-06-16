package com.mirea.kt.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookAdd extends AppCompatActivity implements View.OnClickListener{
    private  DBManager dbManager;
    private EditText editTextShelf, editTextCode, editTextAuthor, editTextRack, editTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);
        this.dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));
        editTextShelf = findViewById(R.id.etShelf);
        editTextCode = findViewById(R.id.etCode);
        editTextAuthor = findViewById(R.id.etAuthor);
        editTextRack = findViewById(R.id.etRack);
        editTextTitle = findViewById(R.id.etTitle);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnList = findViewById(R.id.btnList);
        btnAdd.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd) {
            if (this.dbManager != null) {
                String shelf = editTextShelf.getText().toString();
                String code = editTextCode.getText().toString();
                String author = editTextAuthor.getText().toString();
                String rack = editTextRack.getText().toString();
                String title = editTextTitle.getText().toString();


                if (!shelf.isEmpty() && !code.isEmpty() && !author.isEmpty() && !rack.isEmpty() && !title.isEmpty()) {
                    try {
                        if(Integer.parseInt(shelf) < 1){
                            Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                        }
                        boolean result = dbManager.saveBookToDatabase(new Book(Integer.parseInt(shelf), code, author, Integer.parseInt(rack), title));
                        Log.i("phones", title);
                        if (result) {
                            Toast.makeText(this, R.string.insert_success, Toast.LENGTH_LONG).show();
                            Log.i("phones", "added");
                        } else {
                            Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                            Log.i("phones", "error");
                        }
                        if(Integer.parseInt(shelf) < 1){
                            Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                        }
                    } catch(NumberFormatException e) {
                        Toast.makeText(this, R.string.insert_error, Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(this, R.string.incorrect_value, Toast.LENGTH_LONG).show();
                    Log.i("phones", "incorrect");
                }

            }
        }else if(v.getId() == R.id.btnList){
            startActivity(new Intent(this, BookActivity.class));
            Log.i("crash activ", "pppppp");
        }
    }




}