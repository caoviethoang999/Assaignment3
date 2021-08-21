package com.example.hoangcv2_todo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo")
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "description")
    String description;
    @ColumnInfo(name = "time")
    String time;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "isfinished")
    Boolean isFinished;

    public Todo(String title, String description, String time, String date, Boolean isFinished) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.isFinished = isFinished;
    }

    public Todo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
