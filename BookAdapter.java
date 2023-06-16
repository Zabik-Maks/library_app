package com.mirea.kt.libraryapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Book> books;
    private Context context;

    private MyAppSQLiteHelper sqLiteHelper;
    public SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    private Context parent;

    public BookAdapter(ArrayList<Book> books, Context parent) {
        this.books = books;
        this.parent = parent;
        sqLiteHelper = new MyAppSQLiteHelper(parent.getApplicationContext(), "my_database.db", null, 1);
    }

    public BookAdapter(ArrayList<Book> book) {
        this.books = book;
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView shelfView;
        private final TextView codeView;
        private final TextView authorView;
        private final TextView rackView;
        private final TextView titleView;
        private final ImageView deleteView;

        ViewHolder(View view){
            super(view);
            shelfView = view.findViewById(R.id.tvBookShelf);
            codeView = view.findViewById(R.id.tvBookCode);
            authorView = view.findViewById(R.id.tvBookAuthor);
            rackView = view.findViewById(R.id.tvBookRack);
            titleView = view.findViewById(R.id.tvBookTitle);
            deleteView = view.findViewById(R.id.deleteView);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.shelfView.setText(String.format("%d", book.getShelf()));
        holder.codeView.setText(String.format("%s", book.getCode()));
        holder.authorView.setText(String.format("%s", book.getAuthor()));
        holder.rackView.setText(String.format("%d", book.getRack()));
        holder.titleView.setText(String.format("%s", book.getTitle()));
        holder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper = new MyAppSQLiteHelper(parent, "my_database.db", null, 1);
                database = sqLiteHelper.getReadableDatabase();
                database.delete("TABLE_BOOK", "shelf = ?", new String[]{String.valueOf(book.getShelf())});
                database.delete("TABLE_BOOK", "code = ?", new String[]{String.valueOf(book.getCode())});
                database.delete("TABLE_BOOK", "author = ?", new String[]{book.getAuthor()});
                database.delete("TABLE_BOOK", "rack = ?", new String[]{String.valueOf(book.getRack())});
                database.delete("TABLE_BOOK", "title = ?", new String[]{book.getTitle()});
                books.remove(book);
                notifyItemRemoved(holder.getAdapterPosition());
                Log.i("simple_app_tag", "Слово удалено!");
                Toast.makeText(v.getContext(), "Слово удалено!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
