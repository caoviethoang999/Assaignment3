package com.example.hoangcv2_todo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDAO {
    @Insert
    void insertTodo(Todo todo);

    @Query("SELECT * FROM todo")
    List<Todo> getListTodo();

    @Query("SELECT * FROM todo WHERE title LIKE '%' || :title || '%'")
    List<Todo> search(String title);

    @Query("DELETE FROM todo WHERE isfinished='true'")
    void deleteAll();

    @Delete
    void delete(Todo todo);

    @Query("UPDATE todo SET description = :description,title =:title,time =:time,date=:date,isfinished=:isCompleted WHERE id=:id")
    void updateTodo(String description, String title, String time,String date,Boolean isCompleted,int id);

    @Query("SELECT * FROM todo WHERE isfinished=:check")
    List<Todo> searchIsCompleted(Boolean check);
    @Query("SELECT * FROM todo WHERE isfinished=:check")
    List<Todo> searchNotCompleted(Boolean check);
}
