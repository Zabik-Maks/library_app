package com.mirea.kt.libraryapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DataResponse {
    @SerializedName("result_code")
    @Expose
    private int result;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("task")
    @Expose
    private String task;
    @SerializedName("data")
    @Expose
    private List<Data> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("result_code");
        sb.append('=');

        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("task");
        sb.append('=');
        sb.append(((this.task == null)?"<null>":this.task));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}