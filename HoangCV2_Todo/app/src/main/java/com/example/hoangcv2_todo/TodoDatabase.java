package com.example.hoangcv2_todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "todo.db";
    private static TodoDatabase instance;

    public static synchronized TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TodoDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract TodoDAO TodoDAO();
}
