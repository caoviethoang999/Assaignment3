package com.example.hoangcv2_gym;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Exercise.class}, version = 1)
public abstract class ExerciseDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "excercise.db";
    private static ExerciseDatabase instance;

    public static synchronized ExerciseDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ExerciseDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract ExerciseDAO ExerciseDAO();
}