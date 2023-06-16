package com.mirea.kt.libraryapp;

public class Book {
    private int shelf;
    private String code;
    private String author;
    private int rack;
    private String title;

    public Book(){
    }
    public Book(int shelf, String code, String author, int rack, String title) {
        this.shelf = shelf;
        this.code = code;
        this.author = author;
        this.rack = rack;
        this.title = title;
    }

    public int getShelf() {
        return shelf;
    }
    public String getCode() {
        return code;
    }
    public String getAuthor() {
        return author;
    }
    public int getRack() {
        return rack;
    }
    public String getTitle() {
        return title;
    }


    public void setShelf(int shelf) {
        this.shelf = shelf;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setRack(int rack) {
        this.rack = rack;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
