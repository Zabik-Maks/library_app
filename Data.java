package com.mirea.kt.libraryapp;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("shelf_number")
    @Expose
    private String shelf;
    @SerializedName("vendor_code")
    @Expose
    private String code;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("rack_number")
    @Expose
    private String rack;
    @SerializedName("title")
    @Expose
    private String title;


    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

