package com.example.hoangcv2_gym;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExerciseDAO {
    @Insert
    void insertExercise(Exercise exercise);
    @Delete
    void delete(Exercise exercise);
    @Query("SELECT * FROM exercise WHERE category=:category")
    List<Exercise> getExercise(String category);
    @Query("SELECT * FROM exercise WHERE title LIKE '%' || :title || '%'")
    List<Exercise> searchExercise(String title);
}
