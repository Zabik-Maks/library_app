package com.mirea.kt.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mirea.kt.libraryapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DBManager dbManager;
    int result;
    JSONArray data;
    private JSONObject jsonObject;
    private JSONArray jSONObject;
    private SQLiteDatabase database;
    private SQLiteOpenHelper sqLiteHelper;
    private SharedPreferences sharedPreferences;
    private static final String IS_FIRST_LAUNCH = "is_first_launch";
    private ActivityMainBinding binding;
    private EditText inputPswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSignIn.setOnClickListener(this);
        EditText inputPswd = findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View view) {
        Log.i("simple_app_tag", "Start");
        EditText inpupLog = findViewById(R.id.etLogin);
        EditText inputPswd = findViewById(R.id.etPassword);
        inputPswd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        String login = inpupLog.getText().toString();
        String pswd = inputPswd.getText().toString();

        if (!login.isEmpty() && !pswd.isEmpty()) {
            StdApp.getServerApi().getStudentInfoAll(login, pswd, "RIBO-02-21").enqueue(new Callback<DataResponse>() {
                @Override
                public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                    DataResponse sir = response.body();
                    int result = sir.getResult();
                    if (result == 1) {
                        Intent actIntent = new Intent(getApplicationContext(), BookAdd.class);
                        startActivity(actIntent);
                    } else if (result == -1) {
                        Toast.makeText(getApplicationContext(), "Неверный пароль или логин", Toast.LENGTH_LONG).show();
                        Log.i("simple_app_tag", "Неверный пароль или логин");
                    }
                    Log.i("simple_app_tag", "fjvgjvjgh");

                    sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                    boolean isFirstLaunch = sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true);
                    if (isFirstLaunch) {
                    String shelf1 = sir.getData().get(0).getShelf();
                    String shelf2 = sir.getData().get(1).getShelf();
                    String shelf3 = sir.getData().get(2).getShelf();
                    String code1 = sir.getData().get(0).getCode();
                    String code2 = sir.getData().get(1).getCode();
                    String code3 = sir.getData().get(2).getCode();
                    String author1 = sir.getData().get(0).getAuthor();
                    String author2 = sir.getData().get(1).getAuthor();
                    String author3 = sir.getData().get(2).getAuthor();
                    String rack1 = sir.getData().get(0).getRack();
                    String rack2 = sir.getData().get(1).getRack();
                    String rack3 = sir.getData().get(2).getRack();
                    String title1 = sir.getData().get(0).getTitle();
                    String title2 = sir.getData().get(1).getTitle();
                    String title3 = sir.getData().get(2).getTitle();
                    Log.i("simple_app_tag", "ko;k;l");
                    Book book = new Book();
                    sqLiteHelper = new MyAppSQLiteHelper(getApplicationContext(), "my_database.db", null, 1);
                    database = sqLiteHelper.getReadableDatabase();
                    SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                    Log.i("simple_app_tag", "gbkbgk");
                    ContentValues cv = new ContentValues();
                    ContentValues cv2 = new ContentValues();
                    ContentValues cv3 = new ContentValues();

                    cv.put("shelf", shelf1);
                    cv.put("code", code1);
                    cv.put("author", author1);
                    cv.put("rack", rack1);
                    cv.put("title", title1);

                    cv2.put("shelf", shelf2);
                    cv2.put("code", code2);
                    cv2.put("author", author2);
                    cv2.put("rack", rack2);
                    cv2.put("title", title2);

                    cv3.put("shelf", shelf3);
                    cv3.put("code", code3);
                    cv3.put("author", author3);
                    cv3.put("rack", rack3);
                    cv3.put("title", title3);

                    long rowId = db.insert("TABLE_BOOK", null, cv);
                    long rowId1 = db.insert("TABLE_BOOK", null, cv2);
                    long rowId2 = db.insert("TABLE_BOOK", null, cv3);
                    cv.clear();
                    db.close();
                    Log.i("simple_app_tag", "Загрузка с сервера");
                        sharedPreferences.edit().putBoolean(IS_FIRST_LAUNCH, false).apply();

                    }
                }

                @Override
                public void onFailure(Call<DataResponse> call, Throwable t) {
                    Log.i("simple_app_tag", "error");
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Необходимо заполнить оба поля", Toast.LENGTH_LONG).show();
        }

    }





}