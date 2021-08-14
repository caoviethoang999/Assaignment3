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

    @Query("DELETE FROM todo")
    void deleteAll();

    @Delete
    void delete(Todo todo);

    @Query("UPDATE todo SET description = :description,title =:title WHERE id=:id")
    void updateTodo(String description, String title, int id);
}
